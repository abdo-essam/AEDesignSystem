package com.ae.designsystem.foundation.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Built-in default icon pack providing clean vector icons.
 *
 * Icons are 24×24dp with 2dp stroke, Lucide/Feather inspired.
 * Only uses lineTo/moveTo/curveTo for maximum compatibility.
 */
internal class AEDefaultIconPack : AEIconPack {

    private val cache = mutableMapOf<String, ImageVector>()

    override fun resolve(token: AEIconToken): ImageVector {
        return cache.getOrPut(token.name) {
            when (token.name) {
                "close" -> closeIcon()
                "check" -> checkIcon()
                "chevron_down" -> chevronDownIcon()
                "chevron_up" -> chevronUpIcon()
                "chevron_left" -> chevronLeftIcon()
                "chevron_right" -> chevronRightIcon()
                "search" -> searchIcon()
                "add" -> addIcon()
                "arrow_left" -> arrowLeftIcon()
                "arrow_right" -> arrowRightIcon()
                "arrow_up" -> arrowUpIcon()
                "arrow_down" -> arrowDownIcon()
                "eye" -> eyeIcon()
                "eye_off" -> eyeOffIcon()
                "menu" -> menuIcon()
                "moon" -> moonIcon()
                "sun" -> sunIcon()
                "heart" -> heartIcon()
                "star" -> starIcon()
                "warning" -> warningIcon()
                "info" -> infoIcon()
                "error" -> errorIcon()
                "check_circle" -> checkCircleIcon()
                "edit" -> editIcon()
                "delete" -> deleteIcon()
                "share" -> shareIcon()
                "copy" -> copyIcon()
                "download" -> downloadIcon()
                "upload" -> uploadIcon()
                "refresh" -> refreshIcon()
                "settings" -> settingsIcon()
                "user" -> userIcon()
                "calendar" -> calendarIcon()
                "home" -> homeIcon()
                "more_horizontal" -> moreHorizontalIcon()
                "more_vertical" -> moreVerticalIcon()
                "external_link" -> externalLinkIcon()
                else -> fallbackIcon()
            }
        }
    }

    // ── Icon builder helper ──

    private fun icon(
        name: String,
        block: ImageVector.Builder.() -> Unit,
    ): ImageVector = ImageVector.Builder(
        name = name,
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).apply(block).build()

    private fun ImageVector.Builder.stroke(block: androidx.compose.ui.graphics.vector.PathBuilder.() -> Unit) {
        path(
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        ) {
            block()
        }
    }

    // ── Simple line-based icons ──

    private fun closeIcon() = icon("close") {
        stroke {
            moveTo(18f, 6f); lineTo(6f, 18f)
            moveTo(6f, 6f); lineTo(18f, 18f)
        }
    }

    private fun checkIcon() = icon("check") {
        stroke {
            moveTo(20f, 6f); lineTo(9f, 17f); lineTo(4f, 12f)
        }
    }

    private fun chevronDownIcon() = icon("chevron_down") {
        stroke { moveTo(6f, 9f); lineTo(12f, 15f); lineTo(18f, 9f) }
    }

    private fun chevronUpIcon() = icon("chevron_up") {
        stroke { moveTo(18f, 15f); lineTo(12f, 9f); lineTo(6f, 15f) }
    }

    private fun chevronLeftIcon() = icon("chevron_left") {
        stroke { moveTo(15f, 18f); lineTo(9f, 12f); lineTo(15f, 6f) }
    }

    private fun chevronRightIcon() = icon("chevron_right") {
        stroke { moveTo(9f, 18f); lineTo(15f, 12f); lineTo(9f, 6f) }
    }

    private fun addIcon() = icon("add") {
        stroke {
            moveTo(12f, 5f); lineTo(12f, 19f)
            moveTo(5f, 12f); lineTo(19f, 12f)
        }
    }

    private fun arrowLeftIcon() = icon("arrow_left") {
        stroke {
            moveTo(19f, 12f); lineTo(5f, 12f)
            moveTo(12f, 19f); lineTo(5f, 12f); lineTo(12f, 5f)
        }
    }

    private fun arrowRightIcon() = icon("arrow_right") {
        stroke {
            moveTo(5f, 12f); lineTo(19f, 12f)
            moveTo(12f, 5f); lineTo(19f, 12f); lineTo(12f, 19f)
        }
    }

    private fun arrowUpIcon() = icon("arrow_up") {
        stroke {
            moveTo(12f, 19f); lineTo(12f, 5f)
            moveTo(5f, 12f); lineTo(12f, 5f); lineTo(19f, 12f)
        }
    }

    private fun arrowDownIcon() = icon("arrow_down") {
        stroke {
            moveTo(12f, 5f); lineTo(12f, 19f)
            moveTo(19f, 12f); lineTo(12f, 19f); lineTo(5f, 12f)
        }
    }

    private fun menuIcon() = icon("menu") {
        stroke {
            moveTo(4f, 7f); lineTo(20f, 7f)
            moveTo(4f, 12f); lineTo(20f, 12f)
            moveTo(4f, 17f); lineTo(20f, 17f)
        }
    }

    private fun searchIcon() = icon("search") {
        // Simplified: circle + diagonal line
        stroke {
            // Approximate circle with lines at 8 segments
            moveTo(15.25f, 10.5f)
            lineTo(14.6f, 12.8f); lineTo(12.8f, 14.6f)
            lineTo(10.5f, 15.25f); lineTo(8.2f, 14.6f)
            lineTo(6.4f, 12.8f); lineTo(5.75f, 10.5f)
            lineTo(6.4f, 8.2f); lineTo(8.2f, 6.4f)
            lineTo(10.5f, 5.75f); lineTo(12.8f, 6.4f)
            lineTo(14.6f, 8.2f); lineTo(15.25f, 10.5f)
            // Handle
            moveTo(14.15f, 14.15f); lineTo(20f, 20f)
        }
    }

    private fun eyeIcon() = icon("eye") {
        stroke {
            // Top arc
            moveTo(1f, 12f); lineTo(3f, 9f); lineTo(6f, 7f)
            lineTo(9f, 5.5f); lineTo(12f, 5f); lineTo(15f, 5.5f)
            lineTo(18f, 7f); lineTo(21f, 9f); lineTo(23f, 12f)
            // Bottom arc
            lineTo(21f, 15f); lineTo(18f, 17f); lineTo(15f, 18.5f)
            lineTo(12f, 19f); lineTo(9f, 18.5f); lineTo(6f, 17f)
            lineTo(3f, 15f); lineTo(1f, 12f)
        }
        stroke {
            // Inner circle (pupil) approximation
            moveTo(14f, 12f); lineTo(13.4f, 13.4f); lineTo(12f, 14f)
            lineTo(10.6f, 13.4f); lineTo(10f, 12f); lineTo(10.6f, 10.6f)
            lineTo(12f, 10f); lineTo(13.4f, 10.6f); lineTo(14f, 12f)
        }
    }

    private fun eyeOffIcon() = icon("eye_off") {
        stroke {
            moveTo(1f, 1f); lineTo(23f, 23f)
            moveTo(17.94f, 17.94f); lineTo(15f, 18.5f)
            lineTo(12f, 19f); lineTo(9f, 18.5f); lineTo(6f, 17f)
            lineTo(3f, 15f); lineTo(1f, 12f); lineTo(3f, 9f)
            lineTo(6.06f, 6.06f)
        }
    }

    private fun moonIcon() = icon("moon") {
        stroke {
            // Crescent via lines
            moveTo(21f, 12.79f); lineTo(20.22f, 13.65f)
            lineTo(18f, 15f); lineTo(15f, 15.5f)
            lineTo(12f, 14.5f); lineTo(10f, 12.5f)
            lineTo(9f, 10f); lineTo(9.5f, 7f)
            lineTo(11f, 4.5f); lineTo(14.5f, 2f)
            lineTo(13f, 4f); lineTo(12.95f, 7.02f)
            lineTo(14f, 10f); lineTo(16.5f, 12.5f)
            lineTo(19f, 13.5f); lineTo(21f, 12.79f)
        }
    }

    private fun sunIcon() = icon("sun") {
        stroke {
            // Center circle
            moveTo(16f, 12f); lineTo(14.8f, 14.8f); lineTo(12f, 16f)
            lineTo(9.2f, 14.8f); lineTo(8f, 12f); lineTo(9.2f, 9.2f)
            lineTo(12f, 8f); lineTo(14.8f, 9.2f); lineTo(16f, 12f)
            // Rays
            moveTo(12f, 2f); lineTo(12f, 4f)
            moveTo(12f, 20f); lineTo(12f, 22f)
            moveTo(4.93f, 4.93f); lineTo(6.34f, 6.34f)
            moveTo(17.66f, 17.66f); lineTo(19.07f, 19.07f)
            moveTo(2f, 12f); lineTo(4f, 12f)
            moveTo(20f, 12f); lineTo(22f, 12f)
            moveTo(4.93f, 19.07f); lineTo(6.34f, 17.66f)
            moveTo(17.66f, 6.34f); lineTo(19.07f, 4.93f)
        }
    }

    private fun heartIcon() = icon("heart") {
        stroke {
            moveTo(12f, 21f); lineTo(4f, 14f); lineTo(2.5f, 11.5f)
            lineTo(2f, 9f); lineTo(2.5f, 7f); lineTo(4f, 5.5f)
            lineTo(6f, 4.5f); lineTo(8f, 4.5f); lineTo(10f, 5f)
            lineTo(12f, 7f)
            lineTo(14f, 5f); lineTo(16f, 4.5f); lineTo(18f, 4.5f)
            lineTo(20f, 5.5f); lineTo(21.5f, 7f); lineTo(22f, 9f)
            lineTo(21.5f, 11.5f); lineTo(20f, 14f); lineTo(12f, 21f)
        }
    }

    private fun starIcon() = icon("star") {
        stroke {
            moveTo(12f, 2f); lineTo(14.4f, 8.6f); lineTo(21.5f, 9.2f)
            lineTo(16f, 13.9f); lineTo(17.8f, 21f); lineTo(12f, 17.3f)
            lineTo(6.2f, 21f); lineTo(8f, 13.9f); lineTo(2.5f, 9.2f)
            lineTo(9.6f, 8.6f); lineTo(12f, 2f)
        }
    }

    private fun warningIcon() = icon("warning") {
        stroke {
            // Triangle
            moveTo(12f, 2.5f); lineTo(22f, 19.5f); lineTo(2f, 19.5f); lineTo(12f, 2.5f)
            // Exclamation
            moveTo(12f, 9f); lineTo(12f, 13f)
            moveTo(12f, 17f); lineTo(12.01f, 17f)
        }
    }

    private fun infoIcon() = icon("info") {
        stroke {
            // Circle (octagon approximation)
            moveTo(22f, 12f); lineTo(20.1f, 16.1f); lineTo(16.1f, 20.1f)
            lineTo(12f, 22f); lineTo(7.9f, 20.1f); lineTo(3.9f, 16.1f)
            lineTo(2f, 12f); lineTo(3.9f, 7.9f); lineTo(7.9f, 3.9f)
            lineTo(12f, 2f); lineTo(16.1f, 3.9f); lineTo(20.1f, 7.9f)
            lineTo(22f, 12f)
            // i
            moveTo(12f, 16f); lineTo(12f, 12f)
            moveTo(12f, 8f); lineTo(12.01f, 8f)
        }
    }

    private fun errorIcon() = icon("error") {
        stroke {
            // Circle
            moveTo(22f, 12f); lineTo(20.1f, 16.1f); lineTo(16.1f, 20.1f)
            lineTo(12f, 22f); lineTo(7.9f, 20.1f); lineTo(3.9f, 16.1f)
            lineTo(2f, 12f); lineTo(3.9f, 7.9f); lineTo(7.9f, 3.9f)
            lineTo(12f, 2f); lineTo(16.1f, 3.9f); lineTo(20.1f, 7.9f)
            lineTo(22f, 12f)
            // X
            moveTo(15f, 9f); lineTo(9f, 15f)
            moveTo(9f, 9f); lineTo(15f, 15f)
        }
    }

    private fun checkCircleIcon() = icon("check_circle") {
        stroke {
            // Circle
            moveTo(22f, 12f); lineTo(20.1f, 16.1f); lineTo(16.1f, 20.1f)
            lineTo(12f, 22f); lineTo(7.9f, 20.1f); lineTo(3.9f, 16.1f)
            lineTo(2f, 12f); lineTo(3.9f, 7.9f); lineTo(7.9f, 3.9f)
            lineTo(12f, 2f); lineTo(16.1f, 3.9f); lineTo(20.1f, 7.9f)
            lineTo(22f, 12f)
            // Check
            moveTo(16f, 9f); lineTo(10.5f, 15f); lineTo(8f, 12.5f)
        }
    }

    private fun editIcon() = icon("edit") {
        stroke {
            // Pencil body
            moveTo(17f, 3f); lineTo(21f, 7f); lineTo(8f, 20f); lineTo(3f, 21f); lineTo(4f, 16f); lineTo(17f, 3f)
        }
    }

    private fun deleteIcon() = icon("delete") {
        stroke {
            // Trash can
            moveTo(3f, 6f); lineTo(21f, 6f)
            moveTo(8f, 6f); lineTo(8f, 4f); lineTo(16f, 4f); lineTo(16f, 6f)
            moveTo(5f, 6f); lineTo(6f, 20f); lineTo(18f, 20f); lineTo(19f, 6f)
            moveTo(10f, 10f); lineTo(10f, 16f)
            moveTo(14f, 10f); lineTo(14f, 16f)
        }
    }

    private fun shareIcon() = icon("share") {
        stroke {
            moveTo(4f, 12f); lineTo(4f, 20f); lineTo(20f, 20f); lineTo(20f, 12f)
            moveTo(12f, 3f); lineTo(12f, 15f)
            moveTo(8f, 7f); lineTo(12f, 3f); lineTo(16f, 7f)
        }
    }

    private fun copyIcon() = icon("copy") {
        stroke {
            // Back rect
            moveTo(8f, 8f); lineTo(8f, 3f); lineTo(20f, 3f); lineTo(20f, 15f); lineTo(16f, 15f)
            // Front rect
            moveTo(4f, 9f); lineTo(16f, 9f); lineTo(16f, 21f); lineTo(4f, 21f); lineTo(4f, 9f)
        }
    }

    private fun downloadIcon() = icon("download") {
        stroke {
            moveTo(12f, 3f); lineTo(12f, 15f)
            moveTo(8f, 11f); lineTo(12f, 15f); lineTo(16f, 11f)
            moveTo(4f, 19f); lineTo(20f, 19f)
        }
    }

    private fun uploadIcon() = icon("upload") {
        stroke {
            moveTo(12f, 15f); lineTo(12f, 3f)
            moveTo(16f, 7f); lineTo(12f, 3f); lineTo(8f, 7f)
            moveTo(4f, 19f); lineTo(20f, 19f)
        }
    }

    private fun refreshIcon() = icon("refresh") {
        stroke {
            moveTo(1f, 4f); lineTo(1f, 10f); lineTo(7f, 10f)
            moveTo(23f, 20f); lineTo(23f, 14f); lineTo(17f, 14f)
            moveTo(3.5f, 9f); lineTo(6f, 6f); lineTo(9f, 4.5f)
            lineTo(12f, 4f); lineTo(15f, 4.5f); lineTo(18f, 6.5f); lineTo(20.5f, 10f)
            moveTo(20.5f, 15f); lineTo(18f, 18f); lineTo(15f, 19.5f)
            lineTo(12f, 20f); lineTo(9f, 19.5f); lineTo(6f, 17.5f); lineTo(3.5f, 14f)
        }
    }

    private fun settingsIcon() = icon("settings") {
        stroke {
            // Gear outline (simplified as octagon)
            moveTo(12f, 2f); lineTo(13.5f, 4f); lineTo(16f, 3.5f); lineTo(17.5f, 5.5f)
            lineTo(20f, 6.5f); lineTo(20f, 9f); lineTo(22f, 10.5f); lineTo(22f, 13.5f)
            lineTo(20f, 15f); lineTo(20f, 17.5f); lineTo(17.5f, 18.5f); lineTo(16f, 20.5f)
            lineTo(13.5f, 20f); lineTo(12f, 22f); lineTo(10.5f, 20f); lineTo(8f, 20.5f)
            lineTo(6.5f, 18.5f); lineTo(4f, 17.5f); lineTo(4f, 15f); lineTo(2f, 13.5f)
            lineTo(2f, 10.5f); lineTo(4f, 9f); lineTo(4f, 6.5f); lineTo(6.5f, 5.5f)
            lineTo(8f, 3.5f); lineTo(10.5f, 4f); lineTo(12f, 2f)
            // Center circle
            moveTo(15f, 12f); lineTo(13.6f, 14.2f); lineTo(10.4f, 14.2f)
            lineTo(9f, 12f); lineTo(10.4f, 9.8f); lineTo(13.6f, 9.8f); lineTo(15f, 12f)
        }
    }

    private fun userIcon() = icon("user") {
        stroke {
            // Head circle
            moveTo(16f, 8f); lineTo(15.4f, 9.8f); lineTo(13.6f, 11.2f)
            lineTo(12f, 12f); lineTo(10.4f, 11.2f); lineTo(8.6f, 9.8f)
            lineTo(8f, 8f); lineTo(8.6f, 6.2f); lineTo(10.4f, 4.8f)
            lineTo(12f, 4f); lineTo(13.6f, 4.8f); lineTo(15.4f, 6.2f); lineTo(16f, 8f)
            // Body
            moveTo(4f, 21f); lineTo(5f, 18f); lineTo(7f, 16f)
            lineTo(9.5f, 14.5f); lineTo(12f, 14f); lineTo(14.5f, 14.5f)
            lineTo(17f, 16f); lineTo(19f, 18f); lineTo(20f, 21f)
        }
    }

    private fun calendarIcon() = icon("calendar") {
        stroke {
            // Outer box
            moveTo(4f, 5f); lineTo(20f, 5f); lineTo(20f, 21f); lineTo(4f, 21f); lineTo(4f, 5f)
            // Top hangers
            moveTo(8f, 3f); lineTo(8f, 7f)
            moveTo(16f, 3f); lineTo(16f, 7f)
            // Horizontal line
            moveTo(4f, 10f); lineTo(20f, 10f)
        }
    }

    private fun homeIcon() = icon("home") {
        stroke {
            // Roof
            moveTo(3f, 12f); lineTo(12f, 3f); lineTo(21f, 12f)
            // House body
            moveTo(5f, 11f); lineTo(5f, 20f); lineTo(19f, 20f); lineTo(19f, 11f)
            // Door
            moveTo(9f, 20f); lineTo(9f, 14f); lineTo(15f, 14f); lineTo(15f, 20f)
        }
    }

    private fun moreHorizontalIcon() = icon("more_horizontal") {
        stroke {
            // Three dots (small crosses)
            moveTo(5.5f, 12f); lineTo(6.5f, 12f)
            moveTo(11.5f, 12f); lineTo(12.5f, 12f)
            moveTo(17.5f, 12f); lineTo(18.5f, 12f)
        }
    }

    private fun moreVerticalIcon() = icon("more_vertical") {
        stroke {
            moveTo(12f, 5.5f); lineTo(12f, 6.5f)
            moveTo(12f, 11.5f); lineTo(12f, 12.5f)
            moveTo(12f, 17.5f); lineTo(12f, 18.5f)
        }
    }

    private fun externalLinkIcon() = icon("external_link") {
        stroke {
            moveTo(18f, 13f); lineTo(18f, 19f); lineTo(5f, 19f); lineTo(5f, 6f); lineTo(11f, 6f)
            moveTo(15f, 3f); lineTo(21f, 3f); lineTo(21f, 9f)
            moveTo(10f, 14f); lineTo(21f, 3f)
        }
    }

    private fun fallbackIcon() = icon("fallback") {
        stroke {
            moveTo(4f, 4f); lineTo(20f, 4f); lineTo(20f, 20f)
            lineTo(4f, 20f); lineTo(4f, 4f)
            moveTo(4f, 4f); lineTo(20f, 20f)
        }
    }
}
