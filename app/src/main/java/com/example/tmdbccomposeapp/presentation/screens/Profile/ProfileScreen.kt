package com.example.tmdbccomposeapp.presentation.screens.Profile

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.tmdbccomposeapp.model.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: UserProfileViewModel, navController: NavHostController) {

    LaunchedEffect(Unit) {
        viewModel.getUserToken()
    }
    val userToken = viewModel.userTokenState.collectAsState()
    val sessinonId = viewModel.userSessionState.collectAsState()
    val userAccountState = viewModel.userAccountState.collectAsState()


    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var generatedUserToken by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showBottomSheet){
            ModalBottomSheet(
                modifier = Modifier.wrapContentSize(),
                onDismissRequest = {
                    showBottomSheet = false
                                   if(generatedUserToken.isNotEmpty()) {
                                       viewModel.getSessionId(generatedUserToken)
                                   }
                                   navController.popBackStack()
                                   },
                sheetState = sheetState,
                windowInsets = WindowInsets.waterfall

            ){
                WebViewScreen("https://www.themoviedb.org/authenticate/$generatedUserToken")
            }
        }
        when(val userTokenResult = userToken.value){
            is UIState.Success -> {
                showBottomSheet = true
                generatedUserToken= userTokenResult.data?.requestToken.toString()
            }

            is UIState.Empty ->{}
            is UIState.Error -> {}
            is UIState.Loading -> {}

    }
        when(val session = sessinonId.value){
            is UIState.Success -> {
                showBottomSheet = false
                val generatedSessionId = session.data?.sessionId
                if(generatedSessionId?.isNotEmpty() == true){
                    LaunchedEffect(viewModel){
                        viewModel.getUserAccount(generatedSessionId.toString())
                    }

                }
            }
            is UIState.Empty ->{}
            is UIState.Error -> {}
            is UIState.Loading -> {}
            }

        when(val account = userAccountState.value){
            is UIState.Success -> {
                Text(text = account.data?.username.toString())
                Text(text = account.data?.id.toString())
            }
            is UIState.Empty ->{}
            is UIState.Error -> {}
            is UIState.Loading -> {}

        }
        }
}

    @Composable
    fun WebViewScreen(url: String) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()

                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                }
            },
            update = { webView ->
                webView.loadUrl(url)
            }
        )
    }


