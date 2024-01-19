package com.thuhtooaung.punkbeers.di

import android.content.Context
import androidx.room.Room
import com.thuhtooaung.punkbeers.repo.database.BeerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context = context,
            BeerDatabase::class.java,
            name = "beers.db"
        ).build()
    }

}