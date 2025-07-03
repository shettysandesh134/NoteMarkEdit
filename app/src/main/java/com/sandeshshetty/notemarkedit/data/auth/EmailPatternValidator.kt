package com.sandeshshetty.notemarkedit.data.auth

import android.util.Patterns
import com.sandeshshetty.notemarkedit.domain.auth.PatternValidator

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */
object EmailPatternValidator: PatternValidator {

    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}