package com.amanullah.sampletemplate.base.di.modules

import android.content.Context
import com.amanullah.sampletemplate.data.local.repository.LocalCache
import com.amanullah.sampletemplate.data.local.repository.LocalCacheImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object LocalCacheModule {
    @Provides
    fun provideLocalCache(@ApplicationContext context: Context): LocalCache =
        LocalCacheImpl(context)
}