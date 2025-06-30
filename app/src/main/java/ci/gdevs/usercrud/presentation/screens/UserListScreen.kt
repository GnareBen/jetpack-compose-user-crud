package ci.gdevs.usercrud.presentation.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import ci.gdevs.usercrud.navigation.Screen
import ci.gdevs.usercrud.presentation.components.CustomSearchBar
import ci.gdevs.usercrud.presentation.components.UserCard
import ci.gdevs.usercrud.presentation.viewmodel.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit = {},
    navController: NavHostController
) {

    val users by viewModel.users.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.message, uiState.error) {
        if (uiState.message != null || uiState.error != null) {
            delay(3000)
            viewModel.clearMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { onNavigateToForm() },
                text = { Text("Add User") },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add User") }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                // Search bar
                CustomSearchBar(
                    value = searchQuery,
                    onValueChange = { viewModel.onSearchQueryChange(it) }
                )

                // Affichage du message d'erreur ou de succès
                if (uiState.message != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(
                            text = uiState.message ?: "",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                } else if (uiState.error != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                    ) {
                        Text(
                            text = uiState.error ?: "",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }

                // Liste des utilisateurs
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ){
                    items(users.size) { index ->
                        val user = users[index]
                        UserCard(
                            user = user,
                            onDelete = { viewModel.onDeleteUser(it) },
                            onEdit = {
                                val userJson = Uri.encode(Gson().toJson(user))
                                navController.navigate(Screen.EditUserScreen.createRoute(userJson))
                            }
                        )
                    }
                }
            }
        }
    }

}