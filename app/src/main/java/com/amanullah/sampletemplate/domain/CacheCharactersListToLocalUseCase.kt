package com.amanullah.sampletemplate.domain

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.interactor.UseCase
import com.amanullah.sampletemplate.data.local.models.CacheCharactersListRequestModel
import com.amanullah.sampletemplate.data.local.repository.LocalCache
import javax.inject.Inject

class CacheCharactersListToLocalUseCase @Inject constructor(private val localCache: LocalCache) :
    UseCase<Boolean, CacheCharactersListRequestModel>() {
    override suspend fun run(params: CacheCharactersListRequestModel): AppResult<Boolean> =
        localCache.cacheCharactersListLocally(params)
}