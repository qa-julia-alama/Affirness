package com.example.dicerollerapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicerollerapp.ui.theme.AffirnessApp
import com.example.dicerollerapp.ui.theme.JulkaGray
import kotlin.math.absoluteValue
import kotlin.math.log

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
    val affirmations = listOf(
            stringResource(R.string.affirmation1),
            stringResource(R.string.affirmation2),
            stringResource(R.string.affirmation3),
            stringResource(R.string.affirmation4),
            stringResource(R.string.affirmation5),
            stringResource(R.string.affirmation6)
    )

    val (result, setResult) = remember { mutableStateOf(1) }

    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
                text = affirmations[result - 1],
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 70.dp)
        )
        Button(
                onClick = {
                    val previousResult = result.absoluteValue
                    var newResult = (1..6).random()
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
                    text = stringResource(R.string.roll),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}