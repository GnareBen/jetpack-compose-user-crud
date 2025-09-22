package ci.gdevs.usercrud.navigation

sealed class Screen(val route: String) {
    object UserListScreen : Screen("user_list_screen")
    object FormScreen : Screen("form_screen")
    object EditUserScreen : Screen("edit_user_screen/{user}") {
        fun createRoute(userJson: String): String = "edit_user_screen/$userJson"
    }
}