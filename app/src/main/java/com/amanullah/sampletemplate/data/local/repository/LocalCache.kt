package com.amanullah.sampletemplate.data.local.repository

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.data.local.models.CacheCharactersListRequestModel
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse

/**
 * Responsible to cache data locally for this application.
 */
interface LocalCache {
    /**
     * Cache new state to the local cache.
     * If any error occurs during the cache return FailedToCache failure.
     *
     * @param requestModel Model to be cached.
     *
     * @return [AppResult]<[Boolean]>.
     */
    fun cacheCharactersListLocally(requestModel: CacheCharactersListRequestModel): AppResult<Boolean>

    /**
     * Get cached characters list from the local.
     * If found, parse the cached to the api response model
     * or if not found or error occurs return NotExistInCache failure.
     *
     * @return [AppResult]<[CharactersListAPIResponse]>
     */
    fun getCharactersListFromLocal(): AppResult<CharactersListAPIResponse>
}