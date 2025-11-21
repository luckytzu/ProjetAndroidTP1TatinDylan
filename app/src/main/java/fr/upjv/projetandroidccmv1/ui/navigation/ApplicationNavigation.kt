package fr.upjv.projetandroidccmv1.ui.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.upjv.projetandroidccmv1.ui.screen.MainScreen
import fr.upjv.projetandroidccmv1.ui.screen.SecondScreen
import fr.upjv.projetandroidccmv1.ui.theme.ProjetAndroidCCMV1Theme
import kotlinx.serialization.Serializable

class ApplicationNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetAndroidCCMV1Theme {
                ApplicationNavHost()
            }
        }
    }
}

@Composable
fun ApplicationNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = ApplicationNavigationPath.Home,
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
    ) {
        composable<ApplicationNavigationPath.Home> {
            MainScreen(
                onButtonClick = { navController.navigate(route = ApplicationNavigationPath.Second) }
            )
        }


        composable<ApplicationNavigationPath.Second> {
            SecondScreen(navigateBack = { navController.popBackStack() })
        }
    }
}


object ApplicationNavigationPath {
    @Serializable
    data object Home


    @Serializable
    data object Second
}

