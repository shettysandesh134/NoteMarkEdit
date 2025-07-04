package com.sandeshshetty.notemarkedit.core.presentation.util

import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.domain.util.DataError

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */

fun DataError.asUiText(): UiText {
    return when(this) {
        DataError.Local.DISK_FULL ->
           UiText.StringResource(R.string.error_disk_full)

        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.error_request_timeout)

//        DataError.Network.UNAUTHORIZED -> UiText.StringResource(
//            R.string.
//        )
//        DataError.Network.CONFLICT -> {}
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.error_too_many_requests
        )
        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet
        )
//        DataError.Network.PAYLOAD_TOO_LARGE -> {}
        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.error_server_error
        )
//        DataError.Network.UNKNOWN -> {}
//        DataError.Network.BAD_REQUEST -> TODO()
//        DataError.Network.METHOD_NOT_ALLOWED -> TODO()
        DataError.Network.SERIALIZATION -> UiText.StringResource(
            R.string.error_serialization
        )
        else -> UiText.StringResource(R.string.error_unknown)
    }
}