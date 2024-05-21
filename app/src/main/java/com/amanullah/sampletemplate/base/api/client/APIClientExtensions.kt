package com.amanullah.sampletemplate.base.api.client

import com.amanullah.sampletemplate.base.appresult.AppResult
import com.amanullah.sampletemplate.base.failure.Failure
import retrofit2.Call
import java.net.UnknownHostException

/**
 * Execute a new API request to the server side.
 * If the request is successful then transform the response body to the given object.
 * Else, if something goes wrong return [Failure].
 *
 * @param call [Call] request that need to be executed.
 * @param transform if you want to transform the object into another object.
 * @param default to return the response body as the provided by the object type.
 * @param postRequest if you want to do something more with the response object.
 *
 * @return [AppResult].
 */
fun <T, R> executeAPIRequest(
    call: Call<T>,
    transform: (T) -> R,
    default: T,
    postRequest: (R) -> Unit = {},
): AppResult<R> {
    return try {
        val response = call.execute()
        return when (response.isSuccessful) {
            true -> {
                val transformed = transform((response.body() ?: default))
                postRequest(transformed)
                /*
                We can include our custom parse here. Let's say we manage some error based
                on the response error code which might be feature specific or something else.
                 */
                /*try {
                    return parseCustomAPIResponse(transformed as BaseAPIResponse)
                } catch (e: Exception) {
                    e.printStackTrace()
                    logThis(e.message ?: SOMETHING_WENT_WRONG)
                }*/
                AppResult.Success(transformed)
            }

            false -> AppResult.Error(
                getFailureTypeAccordingToHTTPCode(
                    response.code()
                )
            )
        }
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> {
                AppResult.Error(Failure.HTTP.NetworkConnection)
            }
            // We can write a local log here if we want.
            else -> {
                AppResult.Error(Failure.ActualException(exception))
            }
        }

    }
}

/**
 * Parse function that take a [httpCode] code and return a specific [Failure].
 *
 * @param [httpCode] HTTP Code to determine the error code.
 *
 * @return [Failure] the parsed Result.
 */
internal fun getFailureTypeAccordingToHTTPCode(httpCode: Int): Failure {
    return when (httpCode) {
        400 -> return Failure.HTTP.BadRequest
        401 -> return Failure.HTTP.UnauthorizedError
        403 -> return Failure.HTTP.Forbidden
        404 -> return Failure.HTTP.NotFound
        405 -> return Failure.HTTP.MethodNotAllowed
        429 -> return Failure.HTTP.TooManyRequest
        in 402..409 -> return Failure.HTTP.CanNotConnectToTheServer
        in 500..599 -> return Failure.HTTP.InternalServerError
        else -> Failure.HTTP.CanNotConnectToTheServer
    }
}
