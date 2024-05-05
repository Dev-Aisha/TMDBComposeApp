package com.example.tmdbccomposeapp.presentation.screens.Popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tmdbccomposeapp.Constant
import com.example.tmdbccomposeapp.Constant.Constant.MOVIE_IMAGE_BASE_URL
import com.example.tmdbccomposeapp.R
import com.example.tmdbccomposeapp.model.BackdropSize
import com.example.tmdbccomposeapp.model.Results
import com.example.tmdbccomposeapp.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(popularMoviesState: MutableStateFlow<PagingData<Results>>, navController: NavHostController){
    val moviePadingItems = popularMoviesState.collectAsLazyPagingItems()
    Box{
        Image(
            painter = painterResource(id = R.drawable.home_back),
            contentDescription ="" ,
            modifier = Modifier.fillMaxSize(),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(moviePadingItems.itemCount) { index ->
                if(moviePadingItems[index]?.adult==false){
                    AsyncImage(
                        model =  "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${moviePadingItems[index]?.backdropPath}",
                        contentDescription = "title image",
                        modifier = Modifier
                            .padding(2.dp),
                        contentScale = ContentScale.FillWidth,
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        placeholder = painterResource(id = R.drawable.ic_launcher_background)

                    )
                }
            }
        }

        moviePadingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {

                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = moviePadingItems.loadState.refresh as LoadState.Error
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = error.error.localizedMessage.orEmpty(),
                            modifier = Modifier,
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = moviePadingItems.loadState.append as LoadState.Error
                    Text(
                        text = error.error.localizedMessage.orEmpty(),
                        modifier = Modifier,
                    )
                }
            }
        }
    }

}
