package com.amanullah.sampletemplate.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.amanullah.sampletemplate.BuildConfig
import com.amanullah.sampletemplate.base.composeextensions.SimpleToolbar
import com.amanullah.sampletemplate.base.failure.manager.FailureManager
import com.amanullah.sampletemplate.data.remote.models.BookListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.BookListAPIResponse
import com.amanullah.sampletemplate.extensions.executeBodyOrReturnNull
import com.amanullah.sampletemplate.extensions.mainScope
import com.amanullah.sampletemplate.extensions.nullString
import com.amanullah.sampletemplate.feature.charactersview.CharacterView
import com.amanullah.sampletemplate.feature.charactersview.CharactersViewUIState
import com.amanullah.sampletemplate.utils.MockResponseController
import com.amanullah.sampletemplate.theme.SampleTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : ComponentActivity() {
    @Inject
    lateinit var failureManager: FailureManager

    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) MockResponseController.getBookListMockResponse = true

        setContent {
            SampleTemplateTheme {
                if (vm.uiState.loadData) {
                    vm.uiState.loadData = false

                    LaunchedEffect(key1 = "ListActivity", block = {
                        mainScope {
                            vm.uiState.isLoading = true

                            val result = vm.getBookList(request = BookListAPIRequest(id = 404))

                            if (result.isError()) {
                                vm.uiState.failureMessage =
                                    failureManager.handleFailure(result.asFailure())
                                vm.uiState.isLoading = false
                                return@mainScope
                            } else {
                                val apiResponse = result.asSuccess<BookListAPIResponse>()

                                withContext(Dispatchers.Default) {
                                    val temp = vm.characterListUIState.dataList
                                    temp.addAll(apiResponse.data)
                                    vm.characterListUIState.dataList = temp
                                }

                                vm.uiState.failureMessage = null
                                vm.uiState.isLoading = false
                            }
                        }
                    })
                }

                IntiView(vm.uiState, vm.characterListUIState)
            }

        }

        if (vm.characterListUIState.dataList.isEmpty()) {
            vm.characterListUIState.loadData = true
        }
    }

    @Composable
    private fun IntiView(
        uiState: MainUIState, charaLiUIState: CharactersViewUIState
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            SimpleToolbar(
                title = "Book List", showBackButton = false, showLoading = uiState.isLoading
            )
        }) { values ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(values)
            ) {
                ShowCharactersListUI(uiState = charaLiUIState)
            }
        }
    }


    @Composable
    private fun ShowCharactersListUI(
        uiState: CharactersViewUIState,
        state: LazyListState = rememberLazyListState(),
    ) {
        LazyColumn(
            state = state
        ) {
            items(uiState.dataList.size, key = { uiState.dataList[it].id ?: 0 }) { index ->
                val model = executeBodyOrReturnNull {
                    return@executeBodyOrReturnNull uiState.dataList[index]
                } ?: kotlin.run {
                    null
                }
                model?.let {
                    CharacterView(
                        modifier = Modifier,
                        name = it.name ?: nullString(),
                        imageUrl = it.image ?: "",
                        url = it.url ?: "",
                    )
                }
            }
        }
    }
}

