package ci.gdevs.usercrud.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ci.gdevs.usercrud.data.local.entity.User
import ci.gdevs.usercrud.presentation.screens.EditUserScreen
import ci.gdevs.usercrud.presentation.screens.FormScreen
import ci.gdevs.usercrud.presentation.screens.UserListScreen
import com.google.gson.Gson


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.UserListScreen.route,
    ) {
        composable(route = Screen.UserListScreen.route) {
            UserListScreen(
                onNavigateToForm = { navController.navigate(Screen.FormScreen.route) },
                navController = navController
            )
        }

        composable(route = Screen.FormScreen.route) { backStackEntry ->
            FormScreen(
                onNavigateBack = {
                    navController.navigate(Screen.UserListScreen.route)
                }
            )
        }

        composable(
            route = Screen.EditUserScreen.route,
            arguments = listOf(navArgument("user"){type = NavType.StringType})
        ){ backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")
            val user = Gson().fromJson(userJson, User::class.java)

            EditUserScreen(
                user = user,
                onNavigateBack = {
                    navController.navigate(Screen.UserListScreen.route)
                }
            )

        }
    }
}

