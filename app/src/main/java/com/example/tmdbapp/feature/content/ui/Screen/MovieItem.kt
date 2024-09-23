package com.example.tmdbapp.feature.content.ui.Screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberImagePainter
import com.example.tmdbapp.feature.content.data.model.ContentEvent
import com.example.tmdbapp.feature.content.data.model.ImageUrlConstant
import com.example.tmdbapp.feature.content.data.network.TmdbMovie

@Composable
fun MovieItem(movie: TmdbMovie) {

    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp), shape = RoundedCornerShape(10.dp)
    ) {

        Row(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f)
                    ,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "${ImageUrlConstant.IMAGE_URL_KEY}/${movie.poster_path}",
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
                    .weight(0.6f)
                    .padding(5.dp)
            ) {

                Text(
                    text = movie.title,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(text = movie.overview, maxLines = 5, textAlign = TextAlign.Justify)
                Log.d("MovieOverView", "MovieOverView : ${movie.overview}")

            }
        }


    }


}