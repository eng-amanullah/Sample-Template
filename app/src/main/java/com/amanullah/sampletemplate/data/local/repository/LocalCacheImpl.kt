package com.amanullah.sampletemplate.data.local.repository

import android.content.Context
import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.failure.Failure
import com.amanullah.sampletemplate.data.local.models.CacheCharactersListRequestModel
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.extensions.executeBodyOrReturnNull
import com.rommansabbir.storex.v2.config.StoreXSmartConfig
import com.rommansabbir.storex.v2.smartstorex.SmartStoreX
import com.rommansabbir.storex.v2.strategy.StoreXCachingStrategy
import java.lang.ref.WeakReference
import javax.inject.Inject


class LocalCacheImpl @Inject constructor(private val context: Context) :
    LocalCache {
    override fun cacheCharactersListLocally(requestModel: CacheCharactersListRequestModel): AppResult<Boolean> {
        return executeBodyOrReturnNull {
            val config = getCacheCharactersListConfig(context = context, requestModel)
            SmartStoreX.getInstance.write(config = config)
            AppResult.Success(data = true)
        } ?: kotlin.run {
            AppResult.Error(
                failure = Failure.LocalCache.FailedToCache(
                    message = "Failed to cache."
                )
            )
        }
    }

    override fun getCharactersListFromLocal(): AppResult<CharactersListAPIResponse> {
        return executeBodyOrReturnNull {
            val cachedData = SmartStoreX.getInstance.read<CacheCharactersListRequestModel>(
                config = getCacheCharactersListConfig(
                    context = context,
                    requestModel = CacheCharactersListRequestModel(null, mutableListOf())
                ),
                clazzName = CacheCharactersListRequestModel::class.java
            ) ?: return@executeBodyOrReturnNull AppResult.Error(
                failure = Failure.LocalCache.NotExistInCache
            )
            return@executeBodyOrReturnNull AppResult.Success<CharactersListAPIResponse>(
                data = CharactersListAPIResponse().apply {
                    this.results.addAll(cachedData.list)
                    this.paginationInfo.next = cachedData.paginatedURL
                })
        }
            ?: kotlin.run { AppResult.Error(failure = Failure.LocalCache.NotExistInCache) }
    }

    /**
     * Create a return a new instance of [StoreXSmartConfig] for local caching.
     *
     * @param context Context.
     * @param requestModel Model to be cached to the local.
     */
    private fun getCacheCharactersListConfig(
        context: Context, requestModel: CacheCharactersListRequestModel
    ): StoreXSmartConfig<CacheCharactersListRequestModel> {
        return StoreXSmartConfig(
            context = WeakReference(context),
            fileName = "characters_list",
            xObject = requestModel,
            storeXCachingStrategy = StoreXCachingStrategy.CacheDir,
            overwriteExistingFile = true
        )
    }
}