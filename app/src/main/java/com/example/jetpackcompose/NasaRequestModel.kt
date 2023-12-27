package com.example.jetpackcompose

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Immutable
public data class NasaRequestModel (
    @SerializedName("api_key")
    @Expose
    var key : String? = null,
    @SerializedName("count")
    @Expose
    var count : Int? = null,
) {
    companion object {
        private val TAG = NasaRequestModel::class.java.getSimpleName()
    }

    override fun toString() : String {
        return "$TAG($key, $count)" ?: super.toString()
    }
}