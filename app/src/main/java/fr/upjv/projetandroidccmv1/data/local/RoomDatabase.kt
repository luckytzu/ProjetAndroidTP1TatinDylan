package fr.upjv.projetandroidccmv1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.upjv.projetandroidccmv1.data.local.dao.AndroidVersionDao
import fr.upjv.projetandroidccmv1.data.local.model.AndroidVersionEntity

@Database(
    entities = [
        AndroidVersionEntity::class
    ],
    version = 2,
    exportSchema = false
)

abstract class RoomDatabase : RoomDatabase() {
    abstract fun androidVersionDao(): AndroidVersionDao
}


