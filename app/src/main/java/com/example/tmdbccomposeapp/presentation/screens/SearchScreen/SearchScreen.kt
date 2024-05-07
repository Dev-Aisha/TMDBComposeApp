package com.example.tmdbccomposeapp.presentation.screens.SearchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.filter
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.tmdbccomposeapp.Constant
import com.example.tmdbccomposeapp.Constant.Constant.MOVIE_IMAGE_BASE_URL
import com.example.tmdbccomposeapp.R
import com.example.tmdbccomposeapp.model.BackdropSize
import com.example.tmdbccomposeapp.model.MovieSearch
import com.example.tmdbccomposeapp.model.Results
import com.example.tmdbccomposeapp.presentation.navigation.Screens
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, searchScreenViewModel: SearchScreenViewModel
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()){

        Image(
        painter = painterResource(id = R.drawable.home_back),
        contentDescription ="" ,
        modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
    )

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = query,
            onQueryChange = {query = it},
            onSearch = {active = false
            searchScreenViewModel.getSearch(query) }
            , active = active, onActiveChange = { active = it},
            placeholder = {
                Text(text = "Search movies..")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            },
            modifier = Modifier.fillMaxWidth().background(Color.Transparent)) {
        }

        val moviePagingItems = searchScreenViewModel.searchMovieState.collectAsLazyPagingItems()
        Box{
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement =  Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                items(moviePagingItems.itemCount) { index ->
                    if(moviePagingItems[index]?.adult==false){
                        AsyncImage(
                            model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${moviePagingItems[index]?.posterPath}",
                            contentDescription = "",
                            modifier = Modifier
                                .padding(2.dp)
                                .clickable {
                                    navController.navigate("${Screens.Detail.route}/${moviePagingItems[index]?.id}")
                                },
                            contentScale = ContentScale.FillWidth,
                            error = painterResource(id = R.drawable.ic_launcher_background),
                            placeholder = painterResource(id = R.drawable.ic_launcher_background)
                        )
                    }
                }

            }
            moviePagingItems.apply{
                when{
                    loadState.refresh is LoadState.Loading -> {
                    }
                    loadState.refresh is LoadState.Error -> {
                        val error = moviePagingItems.loadState.refresh as LoadState.Error
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = error.error.localizedMessage.orEmpty(),
                                modifier = Modifier)
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            CircularProgressIndicator()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = moviePagingItems.loadState.append as LoadState.Error
                        Text(text = error.error.localizedMessage.orEmpty(),
                            modifier = Modifier)
                    }
                }
            }
        }
    }
        }

}

//@Composable
//fun SearchScreen(
//    viewModel: SearchMoviesViewModel,
//    onSearchClicked: (String) -> Unit
//) {
//    val searchQuery = remember { mutableStateOf(TextFieldValue()) }
//
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Image(
//            painter = rememberImagePainter(R.drawable.background_image),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            OutlinedTextField(
//                value = searchQuery.value,
//                onValueChange = { searchQuery.value = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Search for movies") }
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = { onSearchClicked(searchQuery.value.text) },
//                modifier = Modifier.align(Alignment.End)
//            ) {
//                Text("Search")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            val searchMoviesState by viewModel.searchMoviesState.collectAsState()
//
//            LazyColumn {
//                items(searchMoviesState) { movie ->
//                    Text(
//                        text = movie.title,
//                        modifier = Modifier.padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}