package com.amanullah.sampletemplate.domain

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.interactor.UseCase
import com.amanullah.sampletemplate.data.remote.models.BookListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse
import com.amanullah.sampletemplate.data.remote.repository.Repository
import javax.inject.Inject

class GetBookListUseCase @Inject constructor(private val repository: Repository) :
    UseCase<BookListAPIResponse, BookListAPIRequest>() {
    override suspend fun run(params: BookListAPIRequest): AppResult<BookListAPIResponse> =
        repository.getBookList(params)
}