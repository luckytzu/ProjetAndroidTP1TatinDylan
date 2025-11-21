package fr.upjv.projetandroidccmv1.architecture

import android.app.Application
import androidx.room.Room
import fr.upjv.projetandroidccmv1.data.local.RoomDatabase

class CustomApplication : Application() {
    companion object {
        lateinit var instance: CustomApplication
    }

    val applicationDatabase: RoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            RoomDatabase::class.java,
            "MyDatabase"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
