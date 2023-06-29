package com.example.dicerollerapp

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicerollerapp.ui.theme.AffirnessApp
import com.example.dicerollerapp.ui.theme.JulkaGray
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirnessApp {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = JulkaGray
                ) {}
                Image(
                    painter = painterResource(R.drawable.white_background), // Zmiana na odpowiedni zasób obrazka
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight()
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    Affirness()
                }
            }
        }
    }
}

@Preview
@Composable
fun Affirness() {
    val affirmations = Affirmations().affirmations
    val (result, setResult) = remember { mutableStateOf(1) }
    val context = LocalContext.current

    val shareLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { launchResult ->
        if (launchResult.resultCode == Activity.RESULT_OK) {
            val affirmation = if (result > 0) affirmations[result - 1] else ""
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Share Affirmation")
                putExtra(Intent.EXTRA_TEXT, affirmation)
            }

            val chooserIntent = Intent.createChooser(shareIntent, "Share")
            val activities = context.packageManager.queryIntentActivities(
                chooserIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            val packageNames = activities.map { it.activityInfo.packageName }.toTypedArray()

            chooserIntent.putExtra(Intent.EXTRA_EXCLUDE_COMPONENTS, packageNames)
            context.startActivity(chooserIntent)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = affirmations[result.absoluteValue - 1],
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 70.dp)
        )
        Button(
            onClick = {
                val previousResult = result.absoluteValue
                var newResult = (1..affirmations.size).random()
                if (previousResult == newResult) {
                    Log.d(TAG, "Affirness: Same result!")
                    if (newResult > 1) {
                        newResult--
                    } else {
                        newResult++
                    }
                }
                setResult(newResult)
            },
        ) {
            Text(
                text = stringResource(R.string.touch),
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                shareLauncher.launch(Bundle())
            },
        ) {
            Text(
                text = stringResource(R.string.share),
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

private fun <I, O> ManagedActivityResultLauncher<I, O>.launch(bundle: Bundle) {

}
