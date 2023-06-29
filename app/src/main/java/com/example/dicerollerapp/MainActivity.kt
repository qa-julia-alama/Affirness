package com.example.dicerollerapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dicerollerapp.ui.theme.AffirnessApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirnessApp {
                Image(
                    painter = painterResource(R.drawable.white_background), // Zmiana na odpowiedni zasób obrazka
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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
            onClick = { setResult((1..6).random()) },
        ) {
            Text(
                text = stringResource(R.string.roll),
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}