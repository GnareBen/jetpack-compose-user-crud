package ci.gdevs.usercrud.di

import android.app.Application
import androidx.room.Room
import ci.gdevs.usercrud.data.local.dao.UserDao
import ci.gdevs.usercrud.data.local.database.AppDatabase
import ci.gdevs.usercrud.data.local.repository.UserRepository
import ci.gdevs.usercrud.data.local.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.UserDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }
}