package ci.gdevs.usercrud.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomFormField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    suportingText: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        value = value,
        onValueChange = onValueChange,
        supportingText = suportingText,
        isError = isError,
        placeholder = { Text(placeholder) },
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            errorIndicatorColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary ,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}