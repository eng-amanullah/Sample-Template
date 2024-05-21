package com.amanullah.sampletemplate.data.local.models

import androidx.annotation.Keep
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.rommansabbir.storex.StoreAbleObject

/**
 * Caching request model which will be stored by StoreX
 * by extending [StoreAbleObject].
 *
 * @param paginatedURL Latest paginated url
 * @param list Latest all characters list
 */
@Keep
data class CacheCharactersListRequestModel(
    val paginatedURL: String?,
    val list: MutableList<CharactersListAPIResponse.Companion.Result>
) : StoreAbleObject()