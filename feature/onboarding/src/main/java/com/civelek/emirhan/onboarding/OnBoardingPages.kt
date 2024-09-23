package com.civelek.emirhan.onboarding

sealed class OnBoardingPages(val headerText: String, val contentText: String) {
    object FirstScreen : OnBoardingPages("Movies", "Movies")
    object SecondScreen : OnBoardingPages("Movies", "Movies")
    object ThirdScreen : OnBoardingPages("Movies", "Movies")


}