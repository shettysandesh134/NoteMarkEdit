package com.sandeshshetty.notemarkedit.domain.auth

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * @author sandeshshetty
 * Created 9/9/25 at {TIME}
 */
class UserDataValidatorTest {

    private lateinit var userDataValidator: UserDataValidator

    @BeforeEach
    fun setup() {
        userDataValidator = UserDataValidator(
            patternValidator = object : PatternValidator{
                override fun matches(value: String): Boolean {
                    return true
                }

            }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "Password123, true",
        "Pad123, false",
        "Password, false",
        "TEST@3, false"
    )
    fun validatePassword(password: String, expectedIsValid: Boolean) {
        val result = userDataValidator.validatePassword(password)
        assertThat(result.isValidPassword).isEqualTo(expectedIsValid)
    }

    @ParameterizedTest
    @CsvSource(
        "sandesh, true",
        "s, false",
        "sas, true",
    )
    fun validateUsername(username: String, expectedIsValid: Boolean) {
        val state = userDataValidator.isValidUsername(username)
        assertThat(state).isEqualTo(expectedIsValid)
    }


}