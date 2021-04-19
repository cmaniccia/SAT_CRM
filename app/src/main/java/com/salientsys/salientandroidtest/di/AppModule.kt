package com.salientsys.salientandroidtest.di

import android.content.Context
import androidx.room.Room
import com.salientsys.salientandroidtest.data.AppDataSource
import com.salientsys.salientandroidtest.data.AppDatabase
import com.salientsys.salientandroidtest.data.AppRepository
import com.salientsys.salientandroidtest.data.network.ApiService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppModule {
    @Singleton
    @Provides
    fun provideAppDataSource(apiService: ApiService): AppDataSource =
        AppDataSource(apiService)

    @Singleton
    @Provides
    fun provideAppRepository(
        database: AppDatabase,
        dataSource: AppDataSource
    ) = AppRepository(database.userDao(), database.postDao(), dataSource)

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "user.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
