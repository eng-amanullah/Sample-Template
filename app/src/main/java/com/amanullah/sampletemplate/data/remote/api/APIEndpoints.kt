package com.amanullah.sampletemplate.data.remote.api

import com.amanullah.sampletemplate.base.api.APIEndpoints
import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterDetailsAPIResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


/**
 * Define all APIs for this demo project.
 *
 * Defined APIs are:
 * - Charters list (Default, Paginated).
 * - Single character detail.
 */
interface APIEndpoints {
    /**
     * Get default characters list from the api.
     *
     * @return [CharactersListAPIResponse] upon on success.
     */
    @GET(APIEndpoints.Characters.Character_Endpoint)
    fun getCharactersListDefault(): Call<CharactersListAPIResponse>

    /**
     * Get characters list from the api (Paginated).
     *
     * @param url Paginated URL
     *
     * @return [CharactersListAPIResponse] upon on success.
     */
    @GET
    fun getCharactersListPaginated(
        @Url url: String
    ): Call<CharactersListAPIResponse>


    /**
     * Get single character detail from the api.
     *
     * @return [SingleCharacterDetailsAPIResponseModel] upon on success.
     */
    @GET("${APIEndpoints.Characters.Character_Endpoint}/{id}")
    fun getCharacterDetail(
        @Path("id") id: Int
    ): Call<SingleCharacterDetailsAPIResponseModel>

    @GET(APIEndpoints.Characters.BOOK_LIST)
    fun getBookList(): Call<BookListAPIResponse>
}