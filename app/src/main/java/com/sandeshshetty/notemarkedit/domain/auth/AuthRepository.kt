package com.sandeshshetty.notemarkedit.domain.auth

import com.sandeshshetty.notemarkedit.domain.util.DataError
import com.sandeshshetty.notemarkedit.domain.util.EmptyResult

/**
 * @author sandeshshetty
 * Created 7/3/25 at {TIME}
 */
interface AuthRepository {
    suspend fun register(username: String, email: String, password: String): EmptyResult<DataError.Network>
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
}