package com.amanullah.sampletemplate.base.di.modules

import android.content.Context
import com.amanullah.sampletemplate.base.failure.manager.FailureManager
import com.amanullah.sampletemplate.base.failure.manager.FailureManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    fun provideFailureManager(@ApplicationContext context: Context): FailureManager =
        FailureManagerImpl(context)
}