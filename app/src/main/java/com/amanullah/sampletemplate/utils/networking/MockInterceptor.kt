package com.amanullah.sampletemplate.utils.networking


import com.amanullah.sampletemplate.base.api.APIEndpoints.Characters.BOOK_LIST
import com.amanullah.sampletemplate.utils.ApplicationContextProvider
import com.amanullah.sampletemplate.utils.MockResponseController
import com.amanullah.sampletemplate.utils.extensions.containsEndpoint
import com.amanullah.sampletemplate.utils.extensions.containsRequestMethod
import com.amanullah.sampletemplate.utils.extensions.loadJSONFromAsset
import com.amanullah.sampletemplate.utils.extensions.processMockResponse
import okhttp3.Interceptor
import okhttp3.Response

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return when {
            MockResponseController.getBookListMockResponse
                    && chain.containsEndpoint(value = BOOK_LIST)
                    && chain.containsRequestMethod("get") -> {
                val responseString =
                    loadJSONFromAsset(
                        ApplicationContextProvider.context!!.applicationContext!!,
                        "book_list.json"
                    )
                processMockResponse(chain, responseString)
            }

            else -> {
                chain.proceed(chain.request())
            }
        }
    }
}