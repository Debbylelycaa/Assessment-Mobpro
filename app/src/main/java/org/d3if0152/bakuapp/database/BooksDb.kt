package org.d3if0152.bakuapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if0152.bakuapp.model.Books

@Database(entities = [Books::class], version = 2, exportSchema = false)
abstract class BooksDb : RoomDatabase() {
    abstract val dao: BooksDao
    companion object{

        @Volatile
        private var INSTANCE: BooksDb? = null

        fun getInstance(context: Context): BooksDb {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooksDb::class.java,
                        "books.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}