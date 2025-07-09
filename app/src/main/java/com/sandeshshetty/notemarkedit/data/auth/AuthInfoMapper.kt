package com.sandeshshetty.notemarkedit.data.auth

import com.sandeshshetty.notemarkedit.domain.auth.AuthInfo

/**
 * @author sandeshshetty
 * Created 7/9/25 at {TIME}
 */

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}