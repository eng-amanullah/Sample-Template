package com.amanullah.sampletemplate.base.appresult

import com.amanullah.sampletemplate.base.appresult.AppResult.Error
import com.amanullah.sampletemplate.base.appresult.AppResult.Success
import com.amanullah.sampletemplate.base.failure.Failure

/**
 * Sealed class that represent the API Results.
 *
 * [AppResult] has two state called [Success] and [Error].
 */
sealed class AppResult<T> {
    /**
     * Represent the success state and return the instance of [AppResult].
     *
     * @param data [T] api response.
     */
    class Success<T>(var data: T?) : AppResult<T>()

    /**
     * Represent the error state and return the instance of [AppResult].
     *
     * @param failure [Failure] to represent the error.
     */
    class Error<T>(var failure: Failure) : AppResult<T>()

    /**
     * To check if the instance of [Failure] is actually a [Failure].
     *
     * @return [Boolean] to determine if error true or false.
     */
    fun isError(): Boolean = this is Error<*>

    /**
     * Force casting to error state. Before making force cast call [isError] API to determine
     * the if it's error or not. If [isError] return true, then it's safe to use this API
     * else it will throw an [Exception].
     *
     * @throws [Exception].
     *
     * @return [Failure].
     */
    @Throws(Exception::class)
    fun asFailure(): Failure {
        return try {
            (this as Error<*>).failure
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Force casting to success state. Before making force cast call [isError] API to determine
     * the if it's error or not. If [isError] return false, then it's safe to use this API
     * else it will throw an [Exception].
     *
     * @throws [Exception].
     *
     * @return [T] actual success response.
     */
    @Throws(Exception::class)
    fun <T> asSuccess(): T {
        return try {
            (this as Success<T>).data!!
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Parse function to get actual state ([Success] or [Error]) as a callback.
     * Before proper callback to be called, actual state is determined by calling
     * [isError] api and based on the result [asFailure] or [asSuccess] API called.
     *
     * @param success Success callback to be invoked of Success.
     * @param error Error callback to be invoked of Error.
     */

    @Throws(Exception::class)
    inline fun <T> parseResult(
        crossinline success: (T) -> Unit,
        crossinline error: (Failure) -> Unit
    ) {
        when (this.isError()) {
            true -> error.invoke(this.asFailure())
            else -> success.invoke(this.asSuccess())
        }
    }
}
