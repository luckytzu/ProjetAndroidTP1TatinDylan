package fr.upjv.projetandroidccmv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import fr.upjv.projetandroidccmv1.ui.navigation.ApplicationNavHost
import fr.upjv.projetandroidccmv1.ui.theme.ProjetAndroidCCMV1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetAndroidCCMV1Theme {
                Box {
                    val navController = rememberNavController()
                    ApplicationNavHost(
                        navController = navController,
                    )
                }
            }
        }
    }
}