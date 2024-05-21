package com.amanullah.sampletemplate.base.di

import android.app.Application
import com.amanullah.sampletemplate.utils.ApplicationContextProvider
import com.rommansabbir.networkx.NetworkXLifecycle
import com.rommansabbir.networkx.NetworkXProvider
import com.rommansabbir.networkx.SmartConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationContextProvider.context = this
        NetworkXProvider.enable(SmartConfig(this, false, NetworkXLifecycle.Application))
    }
}