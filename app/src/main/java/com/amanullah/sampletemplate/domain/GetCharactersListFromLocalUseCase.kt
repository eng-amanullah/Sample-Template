package com.amanullah.sampletemplate.domain

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.interactor.UseCase
import com.amanullah.sampletemplate.data.local.repository.LocalCache
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import javax.inject.Inject

class GetCharactersListFromLocalUseCase @Inject constructor(private val localCache: LocalCache) :
    UseCase<CharactersListAPIResponse, UseCase.None>() {
    override suspend fun run(params: None): AppResult<CharactersListAPIResponse> =
        localCache.getCharactersListFromLocal()
}