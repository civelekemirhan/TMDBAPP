package com.civelek.emirhan.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingColumn(
    backImage: Int,
    content: @Composable () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = backImage), contentScale = ContentScale.Crop)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(0.8f),
                        Color.Black.copy(0.6f),
                        Color.Black.copy(0.4f),
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}