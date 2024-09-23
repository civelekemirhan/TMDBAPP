package com.example.tmdbapp

import com.example.tmdbapp.R


sealed class OnBoardingPages(val headerText: Int, val contentText: Int) {
    object FirstScreen : OnBoardingPages(R.string.onboarding_first_header,R.string.onboarding_first_content)
    object SecondScreen : OnBoardingPages(R.string.onboarding_second_header, R.string.onboarding_second_content)
    object ThirdScreen : OnBoardingPages(R.string.onboarding_third_header, R.string.onboarding_third_content)
}