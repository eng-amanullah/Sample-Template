package com.amanullah.sampletemplate.base.failure.manager

import com.amanullah.sampletemplate.base.failure.Failure

/**
 * Is responsible to manage failure and return the proper error message.
 */
interface FailureManager {
    fun handleFailure(failure: Failure): String
}