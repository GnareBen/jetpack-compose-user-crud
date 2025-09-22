package ci.gdevs.usercrud.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ci.gdevs.usercrud.data.local.entity.User
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: User,
    onDelete: ((User) -> Unit)? = null,
    onEdit: ((User) -> Unit)? = null
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        ListItem(
            colors = ListItemDefaults.colors(containerColor = Color.White),
            headlineContent = {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    fontWeight = FontWeight.Bold
                )
            },
            supportingContent = {
                Text("Ajouté le ${formatDate(user.createdAt)}", fontSize = 12.sp)
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Icône utilisateur"
                )
            },
            trailingContent = {
                Row{
                    IconButton(onClick = { onEdit?.invoke(user) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Modifier"
                        )
                    }
                    IconButton(
                        onClick = { onDelete?.invoke(user) },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color(0xFFF44336))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Supprimer"
                        )
                    }
                }
            },
            modifier = Modifier.padding(6.dp)
        )
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}