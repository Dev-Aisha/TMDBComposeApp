package com.example.tmdbccomposeapp.presentation.screens.DetailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tmdbccomposeapp.Constant
import com.example.tmdbccomposeapp.R
import com.example.tmdbccomposeapp.model.BackdropSize
import com.example.tmdbccomposeapp.model.UIState

@Composable
fun MovieDetailsScreen(int: Int?, viewModel: MovieDetailViewModel) {
    LaunchedEffect(int) {
        if (int != null) {
            viewModel.getMovieDetails(int)
        }
    }
    val result = viewModel.movieDetailState.collectAsState()

    when (val data = result.value) {
        is UIState.Empty -> {}
        is UIState.Error -> {}
        is UIState.Loading -> {}
        is UIState.Success -> {
            LazyColumn {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model="${Constant.Constant.MOVIE_IMAGE_BASE_URL}${BackdropSize.w1280}/${data.data?.backdropPath}",
                            contentDescription = "",
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .heightIn(max = 300.dp),
                            contentScale = ContentScale.FillWidth,
                            error = painterResource(R.drawable.ic_launcher_background),
                            placeholder = painterResource(R.drawable.ic_launcher_background)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomEnd)
                                .padding(top = 120.dp, start = 12.dp, end = 12.dp) // Adjust padding as needed
                        ) {
                            Row(verticalAlignment = Alignment.Bottom) {
                                AsyncImage(
                                    model = "${Constant.Constant.MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${data.data?.posterPath}",
                                    contentDescription = "",
                                    modifier = Modifier
                                    //    .align(Alignment)
                                    ,
                                    contentScale = ContentScale.FillWidth,
                                    error = painterResource(R.drawable.ic_launcher_background),
                                    placeholder = painterResource(R.drawable.ic_launcher_background)
                                )

                                Column {
                                    Text(text = data.data?.genres?.map { it.name }.toString())
                                }
                            }
                        }

                    }
                    Text(text = data.toString())
                    Text(text = "hi")

                }
            }
        }
    }
}