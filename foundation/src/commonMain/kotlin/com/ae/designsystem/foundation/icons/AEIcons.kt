package com.ae.designsystem.foundation.icons

/**
 * Catalog of semantic icon tokens.
 *
 * Components reference these tokens — the actual vector data comes from
 * the active [AEIconPack] provided through [AETheme].
 *
 * ```
 * AEIcon(AEIcons.Heart)
 * AEIcon(AEIcons.Close, contentDescription = "Dismiss")
 * ```
 */
public object AEIcons {
    // ── Navigation ──
    public val ArrowLeft: AEIconToken = AEIconToken("arrow_left")
    public val ArrowRight: AEIconToken = AEIconToken("arrow_right")
    public val ArrowUp: AEIconToken = AEIconToken("arrow_up")
    public val ArrowDown: AEIconToken = AEIconToken("arrow_down")
    public val Menu: AEIconToken = AEIconToken("menu")
    public val Close: AEIconToken = AEIconToken("close")
    public val Search: AEIconToken = AEIconToken("search")
    public val Home: AEIconToken = AEIconToken("home")

    // ── Actions ──
    public val Add: AEIconToken = AEIconToken("add")
    public val Edit: AEIconToken = AEIconToken("edit")
    public val Delete: AEIconToken = AEIconToken("delete")
    public val Share: AEIconToken = AEIconToken("share")
    public val Copy: AEIconToken = AEIconToken("copy")
    public val Download: AEIconToken = AEIconToken("download")
    public val Upload: AEIconToken = AEIconToken("upload")
    public val Refresh: AEIconToken = AEIconToken("refresh")

    // ── Status ──
    public val Check: AEIconToken = AEIconToken("check")
    public val CheckCircle: AEIconToken = AEIconToken("check_circle")
    public val Warning: AEIconToken = AEIconToken("warning")
    public val Error: AEIconToken = AEIconToken("error")
    public val Info: AEIconToken = AEIconToken("info")

    // ── Media / Feedback ──
    public val Heart: AEIconToken = AEIconToken("heart")
    public val Star: AEIconToken = AEIconToken("star")
    public val Eye: AEIconToken = AEIconToken("eye")
    public val EyeOff: AEIconToken = AEIconToken("eye_off")
    public val Play: AEIconToken = AEIconToken("play")
    public val Pause: AEIconToken = AEIconToken("pause")

    // ── Chevrons ──
    public val ChevronDown: AEIconToken = AEIconToken("chevron_down")
    public val ChevronUp: AEIconToken = AEIconToken("chevron_up")
    public val ChevronLeft: AEIconToken = AEIconToken("chevron_left")
    public val ChevronRight: AEIconToken = AEIconToken("chevron_right")

    // ── Misc ──
    public val Settings: AEIconToken = AEIconToken("settings")
    public val User: AEIconToken = AEIconToken("user")
    public val Calendar: AEIconToken = AEIconToken("calendar")
    public val Moon: AEIconToken = AEIconToken("moon")
    public val Sun: AEIconToken = AEIconToken("sun")
    public val MoreHorizontal: AEIconToken = AEIconToken("more_horizontal")
    public val MoreVertical: AEIconToken = AEIconToken("more_vertical")
    public val ExternalLink: AEIconToken = AEIconToken("external_link")
}
