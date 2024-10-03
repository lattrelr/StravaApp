package com.ryanl.stravasramapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ryanl.stravasramapp.ui.MainScreen
import com.ryanl.stravasramapp.ui.theme.StravaSRAMAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StoredAppPrefs.open(applicationContext)

        var code: String? = null
        var scope: String? = null
        val uri: Uri? = intent.data

        if (uri != null) {
            code = uri.getQueryParameter("code")
            scope = uri.getQueryParameter("scope")
        }

        Log.d("MainActivity", "Intent data is $code, $scope")

        setContent {
            StravaSRAMAppTheme {
                MainScreen(code, scope)
            }
        }
    }
}
