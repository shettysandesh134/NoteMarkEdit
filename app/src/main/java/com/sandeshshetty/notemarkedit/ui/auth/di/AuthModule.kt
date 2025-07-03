package com.sandeshshetty.notemarkedit.ui.auth.di

import com.sandeshshetty.notemarkedit.data.auth.EmailPatternValidator
import com.sandeshshetty.notemarkedit.domain.auth.PatternValidator
import com.sandeshshetty.notemarkedit.domain.auth.UserDataValidator
import com.sandeshshetty.notemarkedit.ui.auth.login.LoginViewModel
import com.sandeshshetty.notemarkedit.ui.auth.register.RegisterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @author sandeshshetty
 * Created 6/25/25 at {TIME}
 */

val authModule = module {
    single<PatternValidator>{
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}