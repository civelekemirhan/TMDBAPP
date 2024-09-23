package com.civelek.emirhan.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.civelek.emirhan.onboarding.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding() {


    OnBoardingColumn(backImage = R.drawable.movie_app_background) {
        val viewModel = viewModel<OnBoardingViewModel>()
        val pagerState = rememberPagerState(
            pageCount = {
                viewModel.pageCount
            }
        )
        var isShow by remember{
            mutableStateOf(false)
        }
        val scope= rememberCoroutineScope()

        Column {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(), state = pagerState, contentPadding = PaddingValues()
            ) { page ->


//                HorizontalPagerItem(
//                    pagerState,
//                    page,
//                    viewModel.pages.get(page).headerText,
//                    viewModel.pages.get(page).contentText
//                )


            }
            Spacer(modifier = Modifier.height(20.dp))

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
                        isShow= if(pagerState.pageCount==iter){!isShow} else{isShow}
                    Box(
                        Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .size(16.dp)
                            .background(indicatorColor)
                            .clickable {
                                scope.launch {
                                    pagerState.scrollToPage(2)
                                }

                            }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier=Modifier.fillMaxWidth().fillMaxHeight(1f), horizontalArrangement = Arrangement.Center){
                if(isShow){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Bitir")
                    }
                }

            }
        }

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OnBoardingPreview() {
    OnBoarding()
}
