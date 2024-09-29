package com.example.tmdbapp.feature.content.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.tmdbapp.feature.content.data.model.ImageUrlConstant
import com.example.tmdbapp.feature.content.data.model.TmdbMovie
import com.example.tmdbapp.feature.content.data.room.SaveScreenEvent
import com.example.tmdbapp.feature.content.data.room.TmdbSave


@Composable
fun SaveMovieItem(movie: TmdbSave, onEvent: (SaveScreenEvent) -> Unit, onClick: () -> Unit) {
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .clickable {
                onClick()
            }, shape = RoundedCornerShape(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "${ImageUrlConstant.IMAGE_URL_KEY_W500}/${movie.posterPath}",
                        imageLoader = ImageLoader.Builder(context).crossfade(true).build()
                    ),
                    contentDescription = "Movie Poster",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
                    .padding(5.dp)
            ) {

                Text(
                    text = movie.title,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(text = movie.overView, maxLines = 5, textAlign = TextAlign.Justify)

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.1f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(onClick = {
                    onEvent(SaveScreenEvent.DeleteMovie(movie))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }

            }
        }


    }
}