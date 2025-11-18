//yoyoyo 嗨今天過得好嗎
package com.example.week10activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week10activity.ui.MainScreen
import com.example.week10activity.ui.NavScreens
import com.example.week10activity.ui.SetBirthdayScreen
import com.example.week10activity.ui.ZodiacViewModel
import com.example.week10activity.ui.theme.Week10ActivityTheme // 假設您的 Compose Theme 在這裡

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 使用您的 Compose Theme
            Week10ActivityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ZodiacAppNavigation()
                }
            }
        }
    }
}

@Composable
fun ZodiacAppNavigation() {
    val navController = rememberNavController()
    val viewModel: ZodiacViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavScreens.Main
    ) {
        composable(NavScreens.Main) {
            MainScreen(navController = navController, viewModel = viewModel)
        }

        composable(NavScreens.SetBirthday) {
            SetBirthdayScreen(navController = navController, viewModel = viewModel)
        }
    }
}
