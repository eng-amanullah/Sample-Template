package com.amanullah.sampletemplate.feature

import androidx.lifecycle.ViewModel
import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.interactor.UseCase
import com.amanullah.sampletemplate.data.local.models.CacheCharactersListRequestModel
import com.amanullah.sampletemplate.data.remote.models.BookListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIRequest
import com.amanullah.sampletemplate.data.remote.models.CharactersListAPIResponse
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterAPIRequest
import com.amanullah.sampletemplate.data.remote.models.SingleCharacterDetailsAPIResponseModel
import com.amanullah.sampletemplate.domain.CacheCharactersListToLocalUseCase
import com.amanullah.sampletemplate.domain.GetBookListUseCase
import com.amanullah.sampletemplate.domain.GetCharacterDetailUseCase
import com.amanullah.sampletemplate.domain.GetCharacterListUseCase
import com.amanullah.sampletemplate.domain.GetCharactersListFromLocalUseCase
import com.amanullah.sampletemplate.extensions.nullString
import com.amanullah.sampletemplate.feature.characterdetailview.CharactersDetailsViewUIState
import com.amanullah.sampletemplate.feature.charactersview.CharactersViewUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterListUseCase: GetCharacterListUseCase,
    private val cacheCharactersListToLocalUseCase: CacheCharactersListToLocalUseCase,
    private val getCharactersListFromLocalUseCase: GetCharactersListFromLocalUseCase,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {
    /**
     * Identify if is this the first run of the associated Activity.
     * Based on this client will decide to load characters from
     * the local cache or the remote source (API).
     */
    var isFirstRun: Boolean = false

    /**
     * Lazy instance of [CharactersViewUIState]. ViewModel holds the latest state to represent the
     * characters list UI.
     */
    val characterListUIState by lazy { CharactersViewUIState() }

    /**
     * Lazy instance of [CharactersDetailsViewUIState]. ViewModel holds the latest state to represent the
     * character detail UI.
     */
    val charactersDetailsViewUIState by lazy { CharactersDetailsViewUIState() }

    /**
     * Lazy instance of [MainUIState]. ViewModel holds the latest state to represent the
     * activity UI.
     */
    val uiState by lazy { MainUIState() }

    /**
     * Get new characters list from the remote source (API). Either success or error.
     *
     * @param request Request model.
     *
     * @return [AppResult]<[CharactersListAPIResponse]>
     */
    suspend fun getCharacterListFromRemote(request: CharactersListAPIRequest): AppResult<CharactersListAPIResponse> =
        characterListUseCase(request)

    /**
     * Get character detail from the remote source (API). Either success or error.
     *
     * @param request Request model.
     *
     * @return [AppResult]<[CharactersListAPIResponse]>
     */
    suspend fun getCharacterDetailRemote(request: SingleCharacterAPIRequest): AppResult<SingleCharacterDetailsAPIResponseModel> =
        getCharacterDetailUseCase(request)

    /**
     * Get new characters list from the local cache. Either success or error.
     *
     * @return [AppResult]<[CharactersListAPIResponse]>
     */
    suspend fun getCharactersListFromLocal(): AppResult<CharactersListAPIResponse> =
        getCharactersListFromLocalUseCase(
            UseCase.None()
        )


    /**
     * Cache all characters list to the local including the next paginated url to load data.
     * Either success or error.
     *
     * @param request Request model.
     *
     * @return [AppResult]<[Boolean]>
     */
    suspend fun cacheCharactersListToLocal(request: CacheCharactersListRequestModel) =
        cacheCharactersListToLocalUseCase(request)

    /**
     * Extract the characters details model from
     * the [AppResult]<[SingleCharacterDetailsAPIResponseModel]> and update the
     * list into the [charactersDetailsViewUIState].
     *
     * Make sure all works get done under [Dispatchers.Default].
     *
     * @param result [SingleCharacterDetailsAPIResponseModel] wrapped into [AppResult].
     */
    suspend fun mapAPIResponseToDetailUIState(result: AppResult<SingleCharacterDetailsAPIResponseModel>) {
        withContext(Dispatchers.Default) {
            val apiResponse =
                result.asSuccess<SingleCharacterDetailsAPIResponseModel>()
            charactersDetailsViewUIState.title =
                apiResponse.name ?: nullString()
            charactersDetailsViewUIState.image = apiResponse.image ?: ""
            val infoList = mutableListOf<Pair<String, String>>().apply {
                add(
                    Pair(
                        "Status:",
                        apiResponse.status ?: nullString()
                    )
                )
                add(
                    Pair(
                        "Gender:",
                        apiResponse.gender ?: nullString()
                    )
                )
                add(
                    Pair(
                        "Species:",
                        apiResponse.species ?: nullString()
                    )
                )
                add(
                    Pair(
                        "Origin:",
                        apiResponse.origin?.name ?: nullString()
                    )
                )
                add(
                    Pair(
                        "Location:",
                        apiResponse.location?.name ?: nullString()
                    )
                )
            }
            charactersDetailsViewUIState.informationList.clear()
            charactersDetailsViewUIState.informationList.addAll(infoList)
            charactersDetailsViewUIState.totalEpisodes = apiResponse.episode.size
        }
    }

    suspend fun getBookList(request: BookListAPIRequest) =
        getBookListUseCase(request)
}