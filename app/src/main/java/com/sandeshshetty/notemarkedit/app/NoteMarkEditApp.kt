package com.sandeshshetty.notemarkedit.app

import android.app.Application
import com.sandeshshetty.notemarkedit.app.di.appModule
import com.sandeshshetty.notemarkedit.data.di.dataModule
import com.sandeshshetty.notemarkedit.ui.auth.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author sandeshshetty
 * Created 6/25/25 at {TIME}
 */
class NoteMarkEditApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteMarkEditApp)
            modules(
                appModule,
                authModule,
                dataModule
            )
        }
    }
}