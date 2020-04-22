package dev.b3nedikt.reword

import android.app.Application

open class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
    }
}