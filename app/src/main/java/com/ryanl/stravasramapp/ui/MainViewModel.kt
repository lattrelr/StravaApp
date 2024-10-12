package com.ryanl.stravasramapp.ui

import android.util.Log
import androidx.collection.floatListOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanl.stravasramapp.StoredAppPrefs
import com.ryanl.stravasramapp.api.Api
import com.ryanl.stravasramapp.api.models.Segment
import kotlinx.coroutines.launch

private const val CLIENT_ID = "136628"
private const val CLIENT_SECRET = "2c4f896ac70a26fbadb0c3c513f2565fe74485b8"

class MainViewModel: ViewModel() {
    var segmentList = mutableStateListOf<Segment>()
        private set

    suspend fun getToken(code: String, scope: String) {
        //viewModelScope.launch {
            Api.getToken(code, CLIENT_ID, CLIENT_SECRET)
        //}
    }

    suspend fun fetchRoutes() {
        // TODO List<Float> and ArrayList<Float> give weird output...use string for now
        val chicagoBounds = "41.725094,-87.78882,42.061928,-87.52377"
        //viewModelScope.launch {
            val segmentResp = Api.getSegments(chicagoBounds, "running")
            segmentResp?.let {
                segmentList.addAll(segmentResp.segments)
            }
        //}
    }

    suspend fun authorizeUser(code: String?, scope: String?, startAuth: () -> Unit) {
        val currentTime = System.currentTimeMillis()/1000
        val expiresAt = StoredAppPrefs.getExpires()
        val myToken = StoredAppPrefs.getToken()
        var fetchNewToken = true

        if (myToken != "" && expiresAt != -1L) {
            fetchNewToken = currentTime >= expiresAt
        }

        // TODO refreshToken instead of logging in again to get a code.
        if (fetchNewToken) {
            if (code == null || scope == null) {
                startAuth()
            } else {
                getToken(code, scope)
            }
        } else {
            Log.d("mainViewModel",
                "Have a token: ${StoredAppPrefs.getToken()} ${StoredAppPrefs.getExpires()} ${System.currentTimeMillis()/1000}")
        }
    }
}