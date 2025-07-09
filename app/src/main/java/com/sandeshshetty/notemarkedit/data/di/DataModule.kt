package com.sandeshshetty.notemarkedit.data.di


import android.content.SharedPreferences
import com.sandeshshetty.notemarkedit.data.auth.EncryptedSessionStorage
import com.sandeshshetty.notemarkedit.data.networking.HttpClientFactory
import com.sandeshshetty.notemarkedit.domain.auth.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
/**
 * @author sandeshshetty
 * Created 7/4/25 at {TIME}
 */

val dataModule = module {
    single {
        HttpClientFactory().build()
    }

    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}