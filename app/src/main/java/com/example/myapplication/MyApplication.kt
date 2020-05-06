package com.example.myapplication

import android.app.Application
import android.content.res.Configuration
import com.franmontiel.localechanger.LocaleChanger
import java.util.*

class MyApplication:Application() {
    val SUPPORTED_LOCALES = Arrays.asList(
        Locale("en"),
        Locale("ar")
    )
    override fun onCreate() {
        super.onCreate()
        LocaleChanger.initialize(this, SUPPORTED_LOCALES)
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleChanger.onConfigurationChanged()
    }
}