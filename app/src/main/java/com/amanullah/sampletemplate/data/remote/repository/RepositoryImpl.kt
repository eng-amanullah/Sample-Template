package com.amanullah.sampletemplate.data.remote.repository

import com.amanullah.sampletemplate.base.api.client.executeAPIRequest
import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.data.remote.api.APIService
import com.amanullah.sampletemplate.data.remote.models.BookListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterAPIRequest
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterDetailsAPIResponseModel
import com.amanullah.sampletemplate.data.remote.repository.Repository
import javax.inject.Inject

/**
 * Implementation of [Repository] where the [apiService] is provided via constructor
 * injection with the help of Hilt.
 */
class RepositoryImpl @Inject constructor(private val apiService: APIService) :
    Repository {

    override fun getAllCharacters(request: CharactersListAPIRequest): AppResult<CharactersListAPIResponse> {/*
        Check if @{paginatedURL} is null or not. If null call default api else call the @{paginatedURL}.
         */
        val apiToCall =
            if (request.paginatedURL == null) apiService.getCharactersListDefault() else apiService.getCharactersListPaginated(
                url = request.paginatedURL
            )
        return executeAPIRequest(
            call = apiToCall,
            transform = { it },
            default = CharactersListAPIResponse()
        )
    }

    override fun getSingleCharacterDetails(request: SingleCharacterAPIRequest): AppResult<SingleCharacterDetailsAPIResponseModel> =
        executeAPIRequest(
            call = apiService.getCharacterDetail(request.id),
            transform = { it },
            default = SingleCharacterDetailsAPIResponseModel()
        )

    override fun getBookList(request: BookListAPIRequest): AppResult<BookListAPIResponse> =
        executeAPIRequest(
            call = apiService.getBookList(),
            transform = { it },
            default = BookListAPIResponse()
        )
}