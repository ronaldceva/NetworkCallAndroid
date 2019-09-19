package com.codevinci.ceva.networkcall.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface DefaultService {
    /**
     * Returns a string observable
     */
    @Headers("Cache-Control: no-cache")
    @POST("{params}")
    fun post(@Path(value = "params", encoded = true) url: String): Observable<String>

    @Headers("Cache-Control: no-cache")
    @GET("{params}")
    fun get(@Path(value = "params", encoded = true) url: String): Observable<String>
}