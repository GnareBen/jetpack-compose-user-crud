package ci.gdevs.usercrud.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ci.gdevs.usercrud.data.local.entity.User
import ci.gdevs.usercrud.presentation.viewmodel.UserViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import ci.gdevs.usercrud.presentation.components.CustomFormField

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(
    user: User,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    viewModel: UserViewModel = hiltViewModel()
) {
    // État du formulaire initialisé avec les données de l'utilisateur
    var firstName by rememberSaveable { mutableStateOf(user.firstName) }
    var lastName by rememberSaveable { mutableStateOf(user.lastName) }
    var email by rememberSaveable { mutableStateOf(user.email) }
    var phoneNumber by rememberSaveable { mutableStateOf(user.phoneNumber) }
    var address by rememberSaveable { mutableStateOf(user.address) }

    // État de validation des champs
    var firstNameError by rememberSaveable { mutableStateOf("") }
    var lastNameError by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }
    var phoneNumberError by rememberSaveable { mutableStateOf("") }
    var addressError by rememberSaveable { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.message, uiState.error) {
        if (uiState.message != null) {
            snackbarHostState.showSnackbar(uiState.message ?: "")
            onNavigateBack()
        } else if (uiState.error != null) {
            snackbarHostState.showSnackbar(uiState.error ?: "")
        }
    }

    fun validateAndUpdate() {
        firstNameError = if (firstName.isBlank()) "Le prénom est requis" else ""
        lastNameError = if (lastName.isBlank()) "Le nom est requis" else ""
        emailError = if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) "Email invalide" else ""
        phoneNumberError =
            if (phoneNumber.isBlank() || !android.util.Patterns.PHONE.matcher(phoneNumber)
                    .matches()
            ) "Numéro de téléphone invalide" else ""
        addressError = if (address.isBlank()) "L'adresse est requise" else ""

        if (firstNameError.isEmpty() && lastNameError.isEmpty() && emailError.isEmpty() &&
            phoneNumberError.isEmpty() && addressError.isEmpty()
        ) {
            viewModel.onUpdateUser(
                user.copy(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phoneNumber = phoneNumber,
                    address = address
                )
            )
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text("Modifier l'utilisateur", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar utilisateur
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "${firstName.firstOrNull() ?: ""}${lastName.firstOrNull() ?: ""}",
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Informations personnelles",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = DividerDefaults.Thickness,
                        color = DividerDefaults.color
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomFormField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        suportingText = {
                            Text(
                                text = firstNameError,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        isError = firstNameError.isNotEmpty(),
                        placeholder = "Prénom",
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Personne") },
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomFormField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        suportingText = {
                            Text(
                                text = lastNameError,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        isError = lastNameError.isNotEmpty(),
                        placeholder = "Nom",
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Personne") },
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Coordonnées",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = DividerDefaults.Thickness,
                        color = DividerDefaults.color
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomFormField(
                        value = email,
                        onValueChange = { email = it },
                        suportingText = {
                            Text(
                                text = emailError,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        isError = emailError.isNotEmpty(),
                        placeholder = "Email",
                        singleLine = true,
                        keyboardType = KeyboardType.Email,
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomFormField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        suportingText = {
                            Text(
                                text = phoneNumberError,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        isError = phoneNumberError.isNotEmpty(),
                        placeholder = "Numéro de téléphone",
                        singleLine = true,
                        keyboardType = KeyboardType.Phone,
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone") },
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomFormField(
                        value = address,
                        onValueChange = { address = it },
                        suportingText = {
                            Text(
                                text = addressError,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        isError = addressError.isNotEmpty(),
                        placeholder = "Adresse",
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Address") },
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ElevatedButton(
                onClick = { validateAndUpdate() },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(54.dp)
                    .width(220.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.height(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Mettre à jour",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}