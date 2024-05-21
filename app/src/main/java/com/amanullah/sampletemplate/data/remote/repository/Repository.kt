package com.amanullah.sampletemplate.data.remote.repository

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.data.remote.models.BookListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterAPIRequest
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterDetailsAPIResponseModel

/**
 * Repository that is responsible to provide data for this demo application
 * from either remote or local.
 */
interface Repository {
    /**
     * Get all Rick and Morty Characters (Paginated).
     *
     * @return [AppResult]<[CharactersListAPIResponse]>
     */
    fun getAllCharacters(request: CharactersListAPIRequest): AppResult<CharactersListAPIResponse>

    /**
     * Get single character details.
     *
     * @return [AppResult]<[SingleCharacterDetailsAPIResponseModel]>
     */
    fun getSingleCharacterDetails(request: SingleCharacterAPIRequest): AppResult<SingleCharacterDetailsAPIResponseModel>

    fun getBookList(request: BookListAPIRequest): AppResult<BookListAPIResponse>
}