package com.amanullah.sampletemplate.feature

import androidx.annotation.Keep
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.amanullah.sampletemplate.base.uistate.BaseComposeUIState

@Keep
class MainUIState : BaseComposeUIState() {
    var showDetailsUI: Boolean by mutableStateOf(false)
    var failureMessage: String? by mutableStateOf(null)
}