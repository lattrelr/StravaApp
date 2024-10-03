package com.ryanl.stravasramapp.api

import com.ryanl.stravasramapp.api.models.SegmentResponse
import com.ryanl.stravasramapp.api.models.TokenRequest
import com.ryanl.stravasramapp.api.models.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface StrataService {
    @POST("oauth/token")
    suspend fun getToken(
        @Body request: TokenRequest
    ): Response<TokenResponse>
    @GET("segments/explore")
    suspend fun getSegments(
        @Header("Authorization") token: String,
        @Query("bounds") bounds: String,
        @Query("activity_type") activity_type: String
    ): Response<SegmentResponse>
}