package com.example.dicerollerapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


class Affirmations {

    val affirmations: List<String>
        @Composable
        get() {
            return listOf(
                    stringResource(R.string.affirmation1),
                    stringResource(R.string.affirmation2),
                    stringResource(R.string.affirmation3),
                    stringResource(R.string.affirmation4),
                    stringResource(R.string.affirmation5),
                    stringResource(R.string.affirmation6),
                    stringResource(R.string.affirmation7),
            )
        }

}