package com.sandeshshetty.notemarkedit.domain.util

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */
sealed interface DataError: Error {

    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL
    }
}