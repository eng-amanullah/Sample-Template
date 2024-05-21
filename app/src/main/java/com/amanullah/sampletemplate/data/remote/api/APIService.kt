package com.amanullah.sampletemplate.data.remote.api

import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterDetailsAPIResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Implementation of the [APIEndpoints].
 *
 * The instance of [Retrofit] will be provided by the Hilt through constructor injection.
 *
 * [apiEndpoints] marked lazy to be initialized on-demand which help in terms of memory
 * efficiency in larger scale project.
 */
class APIService @Inject constructor(private val retrofit: Retrofit) :
    APIEndpoints {
    private val apiEndpoints by lazy { retrofit.create(APIEndpoints::class.java) }

    override fun getCharactersListDefault(): Call<CharactersListAPIResponse> =
        apiEndpoints.getCharactersListDefault()

    override fun getCharactersListPaginated(url: String): Call<CharactersListAPIResponse> =
        apiEndpoints.getCharactersListPaginated(url)

    override fun getCharacterDetail(id: Int): Call<SingleCharacterDetailsAPIResponseModel> =
        apiEndpoints.getCharacterDetail(id)

    override fun getBookList(): Call<BookListAPIResponse> =
        apiEndpoints.getBookList()
}