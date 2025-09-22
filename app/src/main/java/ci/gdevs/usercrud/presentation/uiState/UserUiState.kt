package ci.gdevs.usercrud.presentation.uiState

import ci.gdevs.usercrud.data.local.entity.User


data class UserUiState (
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null,
    val message: String? = null,
)