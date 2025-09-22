package ci.gdevs.usercrud.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ci.gdevs.usercrud.data.local.dao.UserDao
import ci.gdevs.usercrud.data.local.entity.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
}