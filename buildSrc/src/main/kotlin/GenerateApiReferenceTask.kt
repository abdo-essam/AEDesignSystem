import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateApiReferenceTask : DefaultTask() {

    @get:InputDirectory
    abstract val componentsDir: DirectoryProperty

    @get:InputDirectory
    abstract val foundationDir: DirectoryProperty

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    private data class ParamInfo(
        val name: String,
        val type: String,
        val default: String?,
        val description: String,
    )

    private data class ComposableInfo(
        val module: String,
        val fileName: String,
        val name: String,
        val description: String,
        val rawSignature: String,
        val params: List<ParamInfo>,
    )

    private fun braceDepthAt(text: String, position: Int): Int {
        var depth = 0
        var inString = false
        var inLineComment = false
        var inBlockComment = false
        var i = 0
        while (i < position && i < text.length) {
            val ch = text[i]
            val next = if (i + 1 < text.length) text[i + 1] else '\u0000'
            when {
                inLineComment -> if (ch == '\n') inLineComment = false
                inBlockComment -> if (ch == '*' && next == '/') {
                    inBlockComment = false
                    i++
                }
                ch == '/' && next == '/' -> inLineComment = true
                ch == '/' && next == '*' -> {
                    inBlockComment = true
                    i++
                }
                ch == '"' -> inString = !inString
                inString -> Unit
                ch == '{' -> depth++
                ch == '}' -> depth--
            }
            i++
        }
        return depth
    }

    private fun extractBalancedParens(text: String, openIndex: Int): String? {
        var depth = 0
        var i = openIndex
        while (i < text.length) {
            when (text[i]) {
                '(' -> depth++
                ')' -> {
                    depth--
                    if (depth == 0) return text.substring(openIndex + 1, i)
                }
            }
            i++
        }
        return null
    }

    private fun findPrecedingKdoc(text: String, composableStart: Int): String {
        val beforeComposable = text.substring(0, composableStart).trimEnd()
        if (!beforeComposable.endsWith("*/")) return ""

        val kdocEnd = beforeComposable.length
        val kdocStart = beforeComposable.lastIndexOf("/**")
        if (kdocStart < 0) return ""

        return beforeComposable.substring(kdocStart + 3, kdocEnd - 2)
    }

    private fun parseKdocDescription(kdoc: String): String {
        if (kdoc.isBlank()) return ""
        val lines = kdoc.lines()
            .map { it.trim().removePrefix("*").trim() }
            .dropWhile { it.isBlank() }

        val descLines = mutableListOf<String>()
        for (line in lines) {
            if (line.startsWith("@")) break
            if (line.isBlank() && descLines.isNotEmpty()) break
            descLines.add(line)
        }
        return descLines.joinToString(" ").trim()
    }

    private fun parseKdocParams(kdoc: String): Map<String, String> {
        if (kdoc.isBlank()) return emptyMap()
        val paramPattern =
            Regex("""@param\s+(\w+)\s+(.+?)(?=@param|@return|@sample|@see|$)""", RegexOption.DOT_MATCHES_ALL)
        return paramPattern.findAll(kdoc).associate { m ->
            val name = m.groupValues[1]
            val desc = m.groupValues[2]
                .lines()
                .joinToString(" ") { it.trim().removePrefix("*").trim() }
                .trim()
                .removeSuffix("*/")
                .trim()
            name to desc
        }
    }

    private fun splitParams(rawParams: String): List<String> {
        val parts = mutableListOf<String>()
        val current = StringBuilder()
        var parenDepth = 0
        var angleDepth = 0
        var braceDepth = 0
        var inString = false
        var i = 0
        while (i < rawParams.length) {
            val ch = rawParams[i]
            when {
                ch == '"' -> {
                    inString = !inString
                    current.append(ch)
                }
                inString -> current.append(ch)
                ch == '(' -> {
                    parenDepth++
                    current.append(ch)
                }
                ch == ')' -> {
                    parenDepth--
                    current.append(ch)
                }
                ch == '<' -> {
                    angleDepth++
                    current.append(ch)
                }
                ch == '>' -> {
                    if (angleDepth > 0) angleDepth--
                    current.append(ch)
                }
                ch == '{' -> {
                    braceDepth++
                    current.append(ch)
                }
                ch == '}' -> {
                    braceDepth--
                    current.append(ch)
                }
                ch == ',' && parenDepth == 0 && angleDepth == 0 && braceDepth == 0 -> {
                    parts.add(current.toString())
                    current.clear()
                }
                else -> current.append(ch)
            }
            i++
        }
        if (current.isNotEmpty()) parts.add(current.toString())
        return parts
    }

    private fun parseSignatureParams(rawParams: String, kdocParams: Map<String, String>): List<ParamInfo> {
        val params = mutableListOf<ParamInfo>()
        val paramStrings = splitParams(rawParams)

        for (paramStr in paramStrings) {
            val trimmed = paramStr.trim()
            if (trimmed.isBlank()) continue
            if (trimmed.startsWith("@") && !trimmed.contains(":")) continue

            val noAnnotations = trimmed.replace(Regex("""@\w+(\([^)]*\))?\s*"""), "").trim()
            if (noAnnotations.isBlank()) continue

            val colonIdx = noAnnotations.indexOf(':')
            if (colonIdx < 0) continue

            val name = noAnnotations.substring(0, colonIdx).trim()
            val typeAndDefault = noAnnotations.substring(colonIdx + 1).trim()
            val eqIdx = typeAndDefault.indexOf('=')
            val type = if (eqIdx >= 0) typeAndDefault.substring(0, eqIdx).trim() else typeAndDefault
            val default = if (eqIdx >= 0) typeAndDefault.substring(eqIdx + 1).trim() else null

            params.add(
                ParamInfo(
                    name = name,
                    type = type,
                    default = default,
                    description = kdocParams[name] ?: "",
                ),
            )
        }

        return params
    }

    private fun cleanSignature(funcName: String, rawParams: String): String {
        val lines = rawParams.lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }
        val compactParams = lines.joinToString(" ").replace(Regex("""\s+"""), " ").trim()
        return "@Composable fun $funcName($compactParams)"
    }

    private fun extractComposables(module: String, file: File): List<ComposableInfo> {
        val text = file.readText()
        val results = mutableListOf<ComposableInfo>()

        val composablePattern = Regex("""@Composable\s+(public\s+)?fun\s+(\w+)\s*\(""")
        for (match in composablePattern.findAll(text)) {
            val funcName = match.groupValues[2]
            val parenStart = match.range.last

            val matchStart = match.range.first
            val lineStart = text.lastIndexOf('\n', matchStart).coerceAtLeast(0)
            val prefix = text.substring(lineStart, matchStart)
            if (prefix.contains("private") || prefix.contains("internal")) continue
            if (braceDepthAt(text, matchStart) > 0) continue

            val rawParams = extractBalancedParens(text, parenStart) ?: continue
            val rawKdoc = findPrecedingKdoc(text, matchStart)

            val description = parseKdocDescription(rawKdoc)
            val kdocParams = parseKdocParams(rawKdoc)
            val params = parseSignatureParams(rawParams, kdocParams)
            val signature = cleanSignature(funcName, rawParams)

            results.add(
                ComposableInfo(
                    module = module,
                    fileName = file.name,
                    name = funcName,
                    description = description,
                    rawSignature = signature,
                    params = params,
                ),
            )
        }

        return results
    }

    private fun kotlinFilesUnder(dir: File): List<File> =
        dir.walkTopDown().filter { it.isFile && it.extension == "kt" }.toList()

    @TaskAction
    fun run() {
        val foundationFiles = kotlinFilesUnder(foundationDir.get().asFile)
        val componentFiles = kotlinFilesUnder(componentsDir.get().asFile)

        val collected = buildList {
            foundationFiles.forEach { addAll(extractComposables("foundation", it)) }
            componentFiles.forEach { addAll(extractComposables("components", it)) }
        }.sortedWith(compareBy<ComposableInfo> { it.module }.thenBy { it.fileName }.thenBy { it.name })

        val md = buildString {
            appendLine("# AEDesignSystem API Reference")
            appendLine()
            appendLine("Generated from KDoc + function signatures in source code.")
            appendLine()

            var currentModule: String? = null
            var currentFile: String? = null

            for (item in collected) {
                if (currentModule != item.module) {
                    currentModule = item.module
                    currentFile = null
                    appendLine("## ${item.module}")
                    appendLine()
                }

                if (currentFile != item.fileName) {
                    currentFile = item.fileName
                    appendLine("### ${item.fileName}")
                    appendLine()
                }

                appendLine("#### ${item.name}")
                appendLine()
                if (item.description.isNotBlank()) {
                    appendLine(item.description)
                    appendLine()
                }
                appendLine("```kotlin")
                appendLine(item.rawSignature)
                appendLine("```")
                appendLine()

                if (item.params.isNotEmpty()) {
                    appendLine("Parameters:")
                    for (p in item.params) {
                        val defaultPart = p.default?.let { " = $it" } ?: ""
                        val descPart = p.description.takeIf { it.isNotBlank() }?.let { " — $it" } ?: ""
                        appendLine("- `${p.name}: ${p.type}$defaultPart`$descPart")
                    }
                    appendLine()
                }
            }
        }

        outputFile.get().asFile.writeText(md)
    }
}
