package com.amanullah.sampletemplate.utils.extensions

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

fun processMockResponse(
    chain: Interceptor.Chain,
    responseString: String,
): Response {
    return chain.proceed(chain.request())
        .newBuilder()
        .code(200)
        .protocol(Protocol.HTTP_2)
        .message(responseString)
        .body(
            responseString.toByteArray()
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        .addHeader("content-type", "application/json")
        .build()
}

fun Interceptor.Chain.containsEndpoint(value: String, ignoreCase: Boolean = true): Boolean {
    return request().url.toUri()
        .toString()
        .contains(value, ignoreCase)
}

fun Interceptor.Chain.containsRequestMethod(value: String, ignoreCase: Boolean = true): Boolean {
    return request().method.contains(value, ignoreCase)
}