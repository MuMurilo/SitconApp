package com.univesp.sitcon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.univesp.sitcon.data.* // Importa suas Entidades

@Database(
    entities = [Usuario::class, AMV::class, Sinais::class, CDV::class, MatriculaValida::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): SitconDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sitcon_database" // Nome interno do banco no Android
                )
                    .createFromAsset("databases/sitcon.db") // ⚠️ Lê o seu arquivo original
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}