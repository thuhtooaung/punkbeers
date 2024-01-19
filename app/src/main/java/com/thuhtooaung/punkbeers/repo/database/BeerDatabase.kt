package com.thuhtooaung.punkbeers.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thuhtooaung.punkbeers.data.local.BeerEntity

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao

}