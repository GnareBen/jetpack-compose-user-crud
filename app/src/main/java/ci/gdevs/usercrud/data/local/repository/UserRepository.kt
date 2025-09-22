package ci.gdevs.usercrud.data.local.repository

import ci.gdevs.usercrud.data.local.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllUsers(): Flow<List<User>>
    suspend fun getUserById(id: Int): User?
    suspend fun insertUser(user: User): Long
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun deleteUserById(id: Int)
    suspend fun searchUsers(query: String): Flow<List<User>>
}