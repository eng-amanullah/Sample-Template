package com.amanullah.sampletemplate.data.remote.models

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
open class BaseAPIResponse : Serializable {
    @SerializedName("status")
    @Expose
    var status: Int = -1

    @SerializedName("message")
    @Expose
    var message: String = ""
}