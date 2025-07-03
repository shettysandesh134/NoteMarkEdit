package com.sandeshshetty.notemarkedit.domain.auth

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */
interface PatternValidator {
    fun matches(value: String): Boolean
}