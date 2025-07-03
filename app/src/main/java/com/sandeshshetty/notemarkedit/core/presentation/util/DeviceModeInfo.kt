package com.sandeshshetty.notemarkedit.core.presentation.util

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * @author sandeshshetty
 * Created 6/26/25 at {TIME}
 */
fun DeviceModeInfo(windowSizeClass: WindowSizeClass): DeviceMode {
    val widthSize = windowSizeClass.windowWidthSizeClass
    val heightSize = windowSizeClass.windowHeightSizeClass

    when {
        widthSize == WindowWidthSizeClass.COMPACT && heightSize == WindowHeightSizeClass.MEDIUM -> {
            return DeviceMode.MOBILE_PORTRAIT
        }
        widthSize == WindowWidthSizeClass.COMPACT && heightSize == WindowHeightSizeClass.EXPANDED -> {
            return DeviceMode.MOBILE_PORTRAIT
        }
        heightSize == WindowHeightSizeClass.COMPACT -> {
            return DeviceMode.MOBILE_LANDSCAPE
        }
        widthSize == WindowWidthSizeClass.MEDIUM && heightSize == WindowHeightSizeClass.EXPANDED -> {
            return DeviceMode.TABLE_PORTRAIT
        }
        widthSize == WindowWidthSizeClass.EXPANDED && heightSize == WindowHeightSizeClass.MEDIUM -> {
            return DeviceMode.TABLET_LANDSCAPE
        }
        else -> {
            return DeviceMode.DESKTOP
        }
    }
}