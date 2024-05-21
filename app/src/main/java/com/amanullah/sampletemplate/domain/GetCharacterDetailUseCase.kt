package com.amanullah.sampletemplate.domain

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.interactor.UseCase
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterAPIRequest
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterDetailsAPIResponseModel
import com.amanullah.sampletemplate.data.remote.repository.Repository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val repository: Repository) :
    UseCase<SingleCharacterDetailsAPIResponseModel, SingleCharacterAPIRequest>() {
    override suspend fun run(params: SingleCharacterAPIRequest): AppResult<SingleCharacterDetailsAPIResponseModel> =
        repository.getSingleCharacterDetails(params)
}