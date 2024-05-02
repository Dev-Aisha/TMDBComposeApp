package com.example.tmdbccomposeapp.presentation.screens.Popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdbccomposeapp.Constant
import com.example.tmdbccomposeapp.R
import com.example.tmdbccomposeapp.model.BackdropSize
import com.example.tmdbccomposeapp.model.UIState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(viewModel: PopularMoviesViewModel, navController: NavHostController){
    when (val result = viewModel.popularMoviesState.value) {
        is UIState.Success -> {
            Box(

            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_back),
                    contentDescription ="" ,
                    modifier = Modifier.fillMaxSize(),
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    items(result.data?.results.orEmpty()) { result ->
                        Column(modifier = Modifier.padding(4.dp)) {

                            GlideImage(
                                model = "${Constant.Constant.MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${result.backdropPath}",
                                contentDescription = "title image",
                                modifier = Modifier
                                    .size(300.dp)
                                    .padding(10.dp)

                            )

                            Text(
                                text = result.title.orEmpty(),
                                color = Color.White,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                modifier = Modifier.padding(10.dp)
                            )

                        }
                    }
                }
            }
        }




        is UIState.Empty -> {
            Text(text = "Empty")
        }
        is UIState.Error -> {
            Text(text = "Error")
        }
        is UIState.Loading -> {
            Text(text = "Loading")
        }

    }
}