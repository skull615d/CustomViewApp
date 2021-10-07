package me.igorfedorov.myapp

import android.app.Application
import me.igorfedorov.myapp.feature.weather_screen.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(appModule)
        }
        // Plant Timber
        Timber.plant(Timber.DebugTree())
    }

}