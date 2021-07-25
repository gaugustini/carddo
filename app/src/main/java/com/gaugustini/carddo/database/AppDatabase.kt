package com.gaugustini.carddo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaugustini.carddo.model.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

}
