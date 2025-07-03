package com.sandeshshetty.notemarkedit.core.presentation.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */
@Stable
sealed interface UiText {
    data class Dynamic(val value: String) : UiText

    @Stable
    class StringResource(
        @StringRes val resId: Int,
        val args: Array<Any> = arrayOf()
    ): UiText

    @Composable
    fun asString(): String {
        return when(this) {
            is Dynamic -> value
            is StringResource -> stringResource(id = resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is Dynamic -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}