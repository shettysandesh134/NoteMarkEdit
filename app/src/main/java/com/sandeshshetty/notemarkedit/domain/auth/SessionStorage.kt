package com.sandeshshetty.notemarkedit.domain.auth

/**
 * @author sandeshshetty
 * Created 7/9/25 at {TIME}
 */
interface SessionStorage {

    suspend fun get(): AuthInfo?

    suspend fun set(authInfo: AuthInfo?)
}