package com.sandeshshetty.notemarkedit.data.di


import com.sandeshshetty.notemarkedit.data.networking.HttpClientFactory
import org.koin.dsl.module
/**
 * @author sandeshshetty
 * Created 7/4/25 at {TIME}
 */

val dataModule = module {
    single {
        HttpClientFactory().build()
    }
}