package com.example.tmdbapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tmdbapp.common.component.authcomponent.AuthButton
import com.example.tmdbapp.navigation.NavigationConstant
import kotlinx.coroutines.launch


@Composable
fun OnBoarding(navController: NavController) {

    val pages = listOf(
        OnBoardingPages.FirstScreen,
        OnBoardingPages.SecondScreen,
        OnBoardingPages.ThirdScreen
    )

    AuthColumn(backImage = R.drawable.movie_app_background) {
        val pagerState = rememberPagerState(
            pageCount = {
                pages.size
            }
        )
        var isShow by remember{
            mutableStateOf(false)
        }
        val scope= rememberCoroutineScope()

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier= Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            ,
            contentAlignment = Alignment.CenterEnd){
            IconButton(modifier=Modifier.padding(end = 20.dp),onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
        }

        Column(modifier= Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalPager(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(), state = pagerState, contentPadding = PaddingValues()
            ) { page ->


                HorizontalPagerItem(
                    pagerState,
                    page,
                    stringResource(id =pages.get(page).headerText),
                    stringResource(id = pages.get(page).contentText)
                )


            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .height(50.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                repeat(pagerState.pageCount) { iter ->
                    val indicatorColor =
                        if (pagerState.currentPage == iter) Color.Red else Color.White

                    Log.d("pagerState.currentPage ","${pagerState.currentPage}")
                        isShow= if(pages.size==pagerState.currentPage+1){true} else {false}
                    Box(
                        Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .size(16.dp)
                            .background(indicatorColor)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier= Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                if(isShow){
                    AuthButton(btnText = "Giriş Yap/Kayıt OL",0.9f,5.dp) {
                        navController.navigate(NavigationConstant.LOGIN_SCREEN_ROUTE_KEY)
                    }
                }

            }
        }

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OnBoardingPreview() {
    OnBoarding(rememberNavController())
}
