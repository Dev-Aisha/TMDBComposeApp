package com.example.tmdbccomposeapp.presentation.screens.onBoardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tmdbccomposeapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import com.example.tmdbccomposeapp.presentation.navigation.Screens
import com.example.tmdbccomposeapp.presentation.navigation.popUpToTop


@Composable
fun OnboardingScreen(onBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    val onBoardingCompleted by onBoardingViewModel.onBoardingCompleted.collectAsState()

    if(onBoardingCompleted){
        navController.navigate(Screens.Home.route){
            popUpToTop(navController)
            }
        }else {
        val currentPageIndex = remember { mutableStateOf(0) }
        val coroutineScope = rememberCoroutineScope()



        LaunchedEffect(Unit) {
            coroutineScope.launch {
                while (true) {
                    delay(2222)
                    currentPageIndex.value = (currentPageIndex.value + 1) % 3
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xff37474f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.onbanding_back),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .align(Alignment.TopCenter)
            )

            OnboardingContent(onBoardingViewModel,currentPageIndex.value, navController)
        }
    }}

        @Composable
        fun OnboardingContent(onBoardingViewModel: OnBoardingViewModel,currentPageIndex: Int, navController: NavHostController) {
            val imageResource = listOf(
                R.drawable.movipage_one,
                R.drawable.movipage_two,
                R.drawable.movipage_three
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.Transparent)
                ) {
                    Image(
                        painter = painterResource(id = imageResource[currentPageIndex]),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = 72.dp)
                            .size(300.dp)
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 350.dp)
                        .requiredHeight(height = 360.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .background(
                            brush = Brush.linearGradient(
                                0f to Color(0xff5a7079),
                                1f to Color(0x39FFFFFF),
                                start = Offset(160f, 0f),
                                end = Offset(152f, 338f)
                            )
                        )
                        .padding(all = 30.dp)
                )
                {
                    OnboardingTextContent(onBoardingViewModel,currentPageIndex, navController)
                }
            }
        }

        @Composable
        fun OnboardingTextContent(onBoardingViewModel: OnBoardingViewModel,currentPageIndex: Int, navCon: NavHostController) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp)
            ) {
                val pages = listOf(
                    Pair(
                        "Welcome\nto CineSpectra\n",
                        "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.\n"
                    ),
                    Pair(
                        "Quick and\nEasy Booking\n",
                        "Reserve your favorite seat in just a few steps. No waiting, no hassle!\n\n"
                    ),
                    Pair(
                        "Tailored Just\nfor You\n",
                        "Personalize your experience! With movie recommendations and exclusive offers, enjoy the cinema your way."
                    )
                )

                Text(
                    text = pages[currentPageIndex].first,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 30.sp,
                        letterSpacing = 0.37.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )

                Text(
                    text = pages[currentPageIndex].second,
                    color = Color(0xfffaef9b),
                    style = TextStyle(
                        fontSize = 16.sp,
                        letterSpacing = 0.37.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)

                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {
                        navCon.navigate(Screens.Home.route) {
                            popUpToTop(navCon)
                        }
                        onBoardingViewModel.saveOnBoardingState(true)
                    }) {
                        Text(
                            text = "Skip",
                            color = Color(0xfffaef9b),
                            fontSize = 16.sp
                        )
                    }
                    Button(
                        onClick = {
                            if (currentPageIndex < pages.size - 1) {
                                currentPageIndex + 1
                            } else {
                                navCon.navigate(Screens.Home.route) {
                                    popUpToTop(navCon)
                                }
                                onBoardingViewModel.saveOnBoardingState(true)

                            }
                        },

                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xff37474f),
                                        Color(0x39FFFFFF)
                                    )
                                ), shape = RoundedCornerShape(30.dp)
                            )
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp)),
                    ) {
                        Text(text = if (currentPageIndex < pages.size - 1) "Next" else "Start")

                    }
                }
                // Badges
                // Implement badges here
            }
        }


