package com.duk.lab.rxandroid.application.restapi.retrofit2

import com.google.gson.annotations.SerializedName

data class Contributor(
        @SerializedName("login") var login: String = "",
        @SerializedName("url") var url: String = "",
        @SerializedName("id") var id: Int = 0) {

    override fun toString(): String {
        return "$login/$url/$id"
    }
}