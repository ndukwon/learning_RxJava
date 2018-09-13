package com.duk.lab.rxandroid.application.restapi.retrofit2

import com.google.gson.annotations.SerializedName

data class Repo(@SerializedName("name") var name: String = "") {
    override fun toString(): String {
        return "$name"
    }
}