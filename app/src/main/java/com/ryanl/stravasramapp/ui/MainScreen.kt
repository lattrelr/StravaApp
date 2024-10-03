package com.ryanl.stravasramapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryanl.stravasramapp.StoredAppPrefs

private const val CLIENT_ID = 136628
private const val REDIRECT_URI = "http://stravasramapp://callback"
private const val SCOPE = "activity:read"

@Composable
fun MainScreen(apiCode: String?, apiScope: String?, mainViewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // TODO Probably not the best place for this logic
        val currentTime = System.currentTimeMillis()/1000
        val expiresAt = StoredAppPrefs.getExpires()
        val myToken = StoredAppPrefs.getToken()
        var fetchNewToken = true

        if (myToken != "" && expiresAt != -1L) {
            fetchNewToken = if (currentTime >= expiresAt) true else false
        }

        // TODO fetch new token should be done in the view model maybe, and use the
        // TODO refreshToken instead of logging in again to get a code.
        if (fetchNewToken) {
            if (apiCode == null || apiScope == null) {
                startAuth(context)
            } else {
                mainViewModel.getToken(apiCode, apiScope)
            }
        } else {
            Log.d("MainScreen",
                "Have a token: ${StoredAppPrefs.getToken()} ${StoredAppPrefs.getExpires()} ${System.currentTimeMillis()/1000}")
        }

        mainViewModel.fetchRoutes()
    }

    AppTitle()
    SegmentList(mainViewModel)
}

@Composable
fun AppTitle() {
    Text(text="Chicago Segments")
}

@Composable
fun SegmentList(mainViewModel: MainViewModel = viewModel()) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(mainViewModel.segmentList) { segment ->
            Text(text = segment.name)
        }
    }
}

fun startAuth(context: Context) {
    val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
        .buildUpon()
        .appendQueryParameter("client_id", "$CLIENT_ID")
        .appendQueryParameter("redirect_uri", REDIRECT_URI)
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("approval_prompt", "auto")
        .appendQueryParameter("scope", SCOPE)
        .build()

    val intent = Intent(Intent.ACTION_VIEW, intentUri)
    context.startActivity(intent)
}