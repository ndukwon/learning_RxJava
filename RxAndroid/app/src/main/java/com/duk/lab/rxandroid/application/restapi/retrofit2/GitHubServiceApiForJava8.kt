package com.duk.lab.rxandroid.application.restapi.retrofit2

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.concurrent.CompletableFuture

interface GitHubServiceApiForJava8 {

    // CompletableFuture makes Runtime errors of this interface for Java7
    @Headers("Accept: application/vnd.github.v3.full+json")
    @GET("repos/{owner}/{repo}/contributors")
    fun getFutureContributorsWithHeader(@Path("owner") owner: String, @Path("repo") repo: String)
            : CompletableFuture<List<Contributor>>
}