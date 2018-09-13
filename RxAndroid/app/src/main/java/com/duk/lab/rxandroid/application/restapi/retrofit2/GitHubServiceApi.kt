package com.duk.lab.rxandroid.application.restapi.retrofit2

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubServiceApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("repos/{owner}/{repo}/contributors")
    fun getObservableContributors(@Path("owner") owner: String, @Path("repo") repo: String)
            : Observable<List<Contributor>>

    @GET("repos/{owner}/{repo}/contributors")
    fun getCallContributors(@Path("owner") owner: String, @Path("repo") repo: String)
            : Call<List<Contributor>>
}