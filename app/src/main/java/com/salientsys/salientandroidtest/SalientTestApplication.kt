package com.salientsys.salientandroidtest

import android.app.Application
import com.salientsys.salientandroidtest.di.AppComponent
import com.salientsys.salientandroidtest.di.DaggerAppComponent
import timber.log.Timber

open class SalientTestApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
