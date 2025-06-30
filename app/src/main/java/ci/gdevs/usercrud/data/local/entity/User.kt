package ci.gdevs.usercrud.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val createdAt: Long = System.currentTimeMillis()
): Parcelable
