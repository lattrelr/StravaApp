package com.ryanl.stravasramapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryanl.stravasramapp.StoredAppPrefs

private const val CLIENT_ID = 136628
private const val REDIRECT_URI = "http://stravasramapp://callback"
private const val SCOPE = "activity:read"

@Composable
fun MainScreen(apiCode: String?, apiScope: String?, mainViewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // TODO could change to await if we want to keep the corouine in the viewmodel
        mainViewModel.authorizeUser(apiCode, apiScope) {
            startAuth(context)
        }
        // TODO this is called after startAuth and fails
        mainViewModel.fetchRoutes()
    }

    Column {
        AppTitle()
        SegmentList(mainViewModel)
    }
}

@Composable
fun AppTitle() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Chicago Segments",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SegmentList(mainViewModel: MainViewModel = viewModel()) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(mainViewModel.segmentList) { segment ->
            Card (
                modifier = Modifier.padding(10.dp).fillMaxWidth()
            ){
                Column (
                    modifier = Modifier.padding(10.dp).fillMaxWidth()
                ){
                    Text(text = segment.name, fontSize = 20.sp)
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Column {
                            Text(text = "Distance")
                            Text(text = "${segment.distance}m")
                        }
                        Column {
                            Text(text = "Elevation Diff")
                            Text(text = "${segment.elev_difference}m")
                        }
                        Column {
                            Text(text = "Avg Grade")
                            Text(text = "${"%.2f".format(segment.avg_grade * 100)}%")
                        }
                    }
                }
            }
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