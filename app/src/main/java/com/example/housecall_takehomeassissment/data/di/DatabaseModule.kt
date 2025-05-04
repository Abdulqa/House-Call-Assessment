package com.example.housecall_takehomeassissment.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.housecall_takehomeassissment.data.localstorage.AppDatabase
import com.example.housecall_takehomeassissment.data.localstorage.dao.DrugDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "drug_database"
        ).build()
    }

    @Provides
    fun provideDrugDao(appDatabase: AppDatabase): DrugDao {
        return appDatabase.drugDao()
    }
}
