package com.amanullah.sampletemplate.data.remote.models

import androidx.annotation.Keep
import com.amanullah.sampletemplate.extensions.writeString
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Keep
class BookListAPIResponse {

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: MutableList<Book> = mutableListOf()

    companion object {

        @Keep
        class Book {
            @SerializedName("id")
            @Expose
            var id: Int? = null

            @SerializedName("name")
            @Expose
            var name: String? = null
                set(value) {
                    field = if (value == null) null else writeString { it.append(value) }
                }

            @SerializedName("image")
            @Expose
            var image: String? = null
                set(value) {
                    field = if (value == null) null else writeString { it.append(value) }
                }

            @SerializedName("url")
            @Expose
            var url: String? = null
                set(value) {
                    field = if (value == null) null else writeString { it.append(value) }
                }
        }
    }
}