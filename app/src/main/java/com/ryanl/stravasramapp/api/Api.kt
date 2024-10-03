package com.ryanl.stravasramapp.api

import android.util.Log
import com.ryanl.stravasramapp.StoredAppPrefs
import com.ryanl.stravasramapp.api.models.SegmentResponse
import com.ryanl.stravasramapp.api.models.TokenRequest
import com.ryanl.stravasramapp.api.models.TokenResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    private const val TAG = "Api"
    private const val BASE_URL = "https://www.strava.com/api/v3/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private val strataService: StrataService by lazy {
        retrofit.create(StrataService::class.java)
    }

    suspend fun getToken(myCode: String, clientId: String, clientSecret: String) {
        val resp = strataService.getToken(
            TokenRequest(
                client_id = clientId,
                client_secret = clientSecret,
                code = myCode,
                grant_type = "authorization_code"
            )
        )

        Log.d(TAG, "Response is ${resp.body()}")

        resp.body()?.let {
            StoredAppPrefs.setToken(it.access_token)
            StoredAppPrefs.setExpires(it.expires_at)
        }
    }

    suspend fun getSegments(myBounds: String, activityType: String): SegmentResponse? {
        Log.d(TAG, "Trying to get segments...")

        val resp = strataService.getSegments(
            token = "Bearer ${StoredAppPrefs.getToken()}",
            bounds = myBounds,
            activity_type = activityType,
        )

        return resp.body()

        /*Log.d(TAG, "Response code is ${resp.code()}")
        Log.d(TAG, "Response code is ${resp.raw().request().url()}")
        Log.d(TAG, "Response code is ${resp.errorBody()?.string()}")
        Log.d(TAG, "Response body is ${resp.body()}")
        Log.d(TAG, "Response body is ${resp.raw().body()}")*/
    }
}
