package ci.gdevs.usercrud.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.gdevs.usercrud.data.local.entity.User
import ci.gdevs.usercrud.data.local.repository.UserRepository
import ci.gdevs.usercrud.presentation.uiState.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState = _uiState.asStateFlow()


    val users = searchQuery.flatMapLatest { query ->
        if (query.isBlank()) {
            userRepository.getAllUsers()
        } else {
            userRepository.searchUsers(query)
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onGetUserById(userId: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, message = null) }
                val user = userRepository.getUserById(userId)
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Erreur lors de la récupération de l'utilisateur: ${e.message}", isLoading = false) }
            }
        }
    }

    fun onAddUser(user: User) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, message = null) }
                userRepository.insertUser(user)
                _uiState.update { it.copy(message = "Utilisateur ajouté avec succès") }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Erreur lors de l'ajout de l'utilisateur: ${e.message}") }
            }
        }
    }

    fun onUpdateUser(user: User) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, message = null) }
                userRepository.updateUser(user)
                _uiState.update { it.copy(message = "Utilisateur mis à jour avec succès") }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Erreur lors de la mise à jour de l'utilisateur: ${e.message}") }
            }
        }
    }

    fun onDeleteUser(user: User) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, message = null) }
                userRepository.deleteUser(user)
                _uiState.update { it.copy(message = "Utilisateur supprimé avec succès") }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Erreur lors de la suppression de l'utilisateur: ${e.message}") }
            }
        }
    }

    fun onDeleteUserById(userId: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, message = null) }
                userRepository.deleteUserById(userId)
                _uiState.update { it.copy(message = "Utilisateur supprimé avec succès") }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Erreur lors de la suppression de l'utilisateur: ${e.message}") }
            }
        }
    }

    fun clearMessage() {
        _uiState.update { it.copy(message = null, error = null) }
    }

}