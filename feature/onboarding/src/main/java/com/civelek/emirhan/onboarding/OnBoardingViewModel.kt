package com.civelek.emirhan.onboarding

import androidx.lifecycle.ViewModel

class OnBoardingViewModel: ViewModel() {

     val pages= listOf(OnBoardingPages.FirstScreen,OnBoardingPages.SecondScreen,OnBoardingPages.ThirdScreen)
    val pageCount=pages.size


}