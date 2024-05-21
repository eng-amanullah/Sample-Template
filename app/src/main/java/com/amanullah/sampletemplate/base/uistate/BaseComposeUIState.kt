package com.amanullah.sampletemplate.base.uistate

import androidx.annotation.Keep
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Base compose UI state for the application.
 * Comes with some default state like [isLoading], [loadData], [showRootView].
 */
@Keep
open class BaseComposeUIState {
    var isLoading by mutableStateOf(false)
    var loadData by mutableStateOf(true)
    var showRootView by mutableStateOf(false)
}