import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

// ---------------------------------------------------------------------------
// API Reference generation: reads KDocs from component sources and writes
// AEDESIGNSYSTEM_REFERENCE.md for AI agents and developers.
// ---------------------------------------------------------------------------
val generateApiReference by tasks.registering(GenerateApiReferenceTask::class) {
    componentsUiDir.set(
        layout.projectDirectory.dir(
            "../../components/src/commonMain/kotlin/com/ae/designsystem/components/ui",
        ),
    )
    foundationDir.set(
        layout.projectDirectory.dir(
            "../../foundation/src/commonMain/kotlin/com/ae/designsystem/foundation",
        ),
    )
    registrySourceFile.set(
        layout.projectDirectory.file(
            "src/commonMain/kotlin/com/ae/designsystem/sample/docs/catalog/ComponentRegistry.kt",
        ),
    )
    outputFile.set(
        layout.projectDirectory.file("../../AEDESIGNSYSTEM_REFERENCE.md"),
    )
}

// ---------------------------------------------------------------------------
// Registry JSON generation: reads component .kt files, outputs static JSON
// to /r/ so the website and CLI share one source of truth.
// ---------------------------------------------------------------------------
val generateRegistryJson by tasks.registering(GenerateRegistryJsonTask::class) {
    componentsUiDir.set(
        layout.projectDirectory.dir(
            "../../components/src/commonMain/kotlin/com/ae/designsystem/components/ui",
        ),
    )
    registrySourceFile.set(
        layout.projectDirectory.file(
            "src/commonMain/kotlin/com/ae/designsystem/sample/docs/catalog/ComponentRegistry.kt",
        ),
    )
    outputDir.set(
        layout.projectDirectory.dir("src/wasmJsMain/resources/r"),
    )
    foundationVersion.set(
        providers.gradleProperty("VERSION_NAME"),
    )
}

tasks.matching { it.name.contains("wasmJsBrowser") && it.name.contains("Distribution") }.configureEach {
    dependsOn(generateRegistryJson)
}

tasks.matching { it.name == "wasmJsProcessResources" }.configureEach {
    dependsOn(generateRegistryJson)
}

val generateComponentSources by tasks.registering(GenerateComponentSourcesTask::class) {
    componentsUiDir.set(
        layout.projectDirectory.dir(
            "../../components/src/commonMain/kotlin/com/ae/designsystem/components/ui",
        ),
    )
    outputDir.set(
        layout.buildDirectory.dir("generated/componentSources/kotlin"),
    )
}





android {
    namespace = "com.ae.designsystem.sample"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.ae.designsystem.sample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            kotlin.srcDir(generateComponentSources.flatMap { it.outputDir })
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.animation)
                implementation(compose.components.resources)
                implementation(projects.foundation)
                implementation(projects.components)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.navigation.compose)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.compose.ui)
                implementation("androidx.activity:activity-compose:1.9.3")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.ae.designsystem.sample.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "AEDesignSystem Catalog"
            packageVersion = "1.0.0"
        }
    }
}
