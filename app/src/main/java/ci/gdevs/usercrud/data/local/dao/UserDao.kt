package ci.gdevs.usercrud.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.gdevs.usercrud.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY createdAt DESC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User) : Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("SELECT * FROM users WHERE firstName LIKE '%' || :query || '%' OR email LIKE '%' || :query || '%' OR lastName LIKE '%' || :query || '%' OR phoneNumber LIKE '%' || :query || '%' OR address LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchUsers(query: String): Flow<List<User>>
}