package com.amanullah.sampletemplate.base.di.modules

import com.amanullah.sampletemplate.data.remote.api.APIService
import com.amanullah.sampletemplate.data.remote.repository.Repository
import com.amanullah.sampletemplate.data.remote.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object APIModule {
    @Provides
    fun provideRickMortyRepository(apiService: APIService): Repository =
        RepositoryImpl(apiService)
}