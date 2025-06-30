package ci.gdevs.usercrud.data.local.repository.impl

import ci.gdevs.usercrud.data.local.dao.UserDao
import ci.gdevs.usercrud.data.local.entity.User
import ci.gdevs.usercrud.data.local.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    override suspend fun getUserById(id: Int): User? = userDao.getUserById(id)

    override suspend fun insertUser(user: User): Long = userDao.insertUser(user)

    override suspend fun updateUser(user: User) = userDao.updateUser(user)

    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    override suspend fun deleteUserById(id: Int) = userDao.deleteUserById(id)

    override suspend fun searchUsers(query: String): Flow<List<User>> = userDao.searchUsers(query)
}