package com.ryanl.stravasramapp.ui

import androidx.collection.floatListOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryanl.stravasramapp.api.Api
import com.ryanl.stravasramapp.api.models.Segment
import kotlinx.coroutines.launch

private const val CLIENT_ID = "136628"
private const val CLIENT_SECRET = "2c4f896ac70a26fbadb0c3c513f2565fe74485b8"

class MainViewModel: ViewModel() {
    var segmentList = mutableStateListOf<Segment>()
        private set

    fun getToken(code: String, scope: String) {
        viewModelScope.launch {
            Api.getToken(code, CLIENT_ID, CLIENT_SECRET)
        }
    }

    fun fetchRoutes() {
        // TODO List<Float> and ArrayList<Float> give weird output...use string for now
        val chicagoBounds = "41.725094,-87.78882,42.061928,-87.52377"
        viewModelScope.launch {
            val segmentResp = Api.getSegments(chicagoBounds, "running")
            segmentResp?.let {
                segmentList.addAll(segmentResp.segments)
            }
        }
    }
}