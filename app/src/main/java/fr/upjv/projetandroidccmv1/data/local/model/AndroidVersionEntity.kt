package fr.upjv.projetandroidccmv1.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "android_version")
data class AndroidVersionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "use")
    val use: Boolean,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "api_level")
    val apiLevel: Int,

    )
