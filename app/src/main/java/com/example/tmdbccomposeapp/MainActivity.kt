package com.example.tmdbccomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdbccomposeapp.Constant.Constant.MOVIE_IMAGE_BASE_URL
import com.example.tmdbccomposeapp.model.BackdropSize
import com.example.tmdbccomposeapp.model.UIState
import com.example.tmdbccomposeapp.presentation.screens.Popular.PopularMoviesViewModel
import com.example.tmdbccomposeapp.ui.theme.GreenBlue
import com.example.tmdbccomposeapp.ui.theme.TMDBCcomposeAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBCcomposeAppTheme {
                val viewModel by viewModels<PopularMoviesViewModel>()
                when (val result = viewModel.popularMoviesState.value) {
                    is UIState.Success -> {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(GreenBlue)
                            .paint(
                                painterResource(id = R.drawable.background),
                                )
                            )
                            
                            
                        {
                            
                        }
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item{
                            }
                            items(result.data?.results.orEmpty()) {
                                GlideImage(
                                    model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${it.posterPath}",
                                    contentDescription = "title image",

                                )
                            }

                            items(result.data?.results.orEmpty()) { result ->
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = result.title.orEmpty(),
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }
                            }
                        }
                    }


                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}

                }
            }
        }
    }
}

