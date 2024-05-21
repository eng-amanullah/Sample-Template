package com.amanullah.sampletemplate.domain

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.interactor.UseCase
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.data.remote.repository.Repository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val repository: Repository) :
    UseCase<CharactersListAPIResponse, CharactersListAPIRequest>() {
    override suspend fun run(params: CharactersListAPIRequest): AppResult<CharactersListAPIResponse> =
        repository.getAllCharacters(params)
}