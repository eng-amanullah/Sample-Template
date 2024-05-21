package com.amanullah.sampletemplate.feature.charactersview

import androidx.annotation.Keep
import androidx.compose.runtime.mutableStateListOf
import com.amanullah.sampletemplate.base.uistate.BaseComposeUIState
import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse


@Keep
class CharactersViewUIState : BaseComposeUIState() {
    var dataList: MutableList<BookListAPIResponse.Companion.Book> =
        mutableStateListOf()
}