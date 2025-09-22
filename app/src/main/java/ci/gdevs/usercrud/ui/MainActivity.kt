package ci.gdevs.usercrud.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ci.gdevs.usercrud.navigation.AppNavGraph
import ci.gdevs.usercrud.presentation.theme.UserCRUDTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserCRUDTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}