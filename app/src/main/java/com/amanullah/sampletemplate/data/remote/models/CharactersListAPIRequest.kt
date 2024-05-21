package com.amanullah.sampletemplate.data.remote.models

import androidx.annotation.Keep

@Keep
data class CharactersListAPIRequest(val fetchFromLocal: Boolean, val paginatedURL: String?)