package com.example.tmdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.tmdbapp.navigation.SetupNavGraph
import com.example.tmdbapp.ui.theme.TmdbAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel=viewModels<MainViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return MainViewModel() as T
                    }
                }
            }
        )



        enableEdgeToEdge(
           statusBarStyle =  SystemBarStyle.dark(mainViewModel.value.statusBarColor.value),
           navigationBarStyle =  SystemBarStyle.dark(mainViewModel.value.navigationBarColor.value)
        )
        setContent {


            TmdbAppTheme {
                SetupNavGraph(navController = rememberNavController())
            }
        }
    }
}

