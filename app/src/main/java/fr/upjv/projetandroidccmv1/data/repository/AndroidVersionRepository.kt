package fr.upjv.projetandroidccmv1.data.repository

import fr.upjv.projetandroidccmv1.architecture.CustomApplication
import fr.upjv.projetandroidccmv1.data.local.model.AndroidVersionEntity
import fr.upjv.projetandroidccmv1.data.mapping.toDataObject
import fr.upjv.projetandroidccmv1.data.mapping.toRoomObject
import fr.upjv.projetandroidccmv1.data.model.MyAndroidModelData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidVersionRepository {
    private val androidVersionDao = CustomApplication.instance.applicationDatabase.androidVersionDao()

    fun selectAllAndroidVersion(): Flow<List<MyAndroidModelData>> {
        return androidVersionDao.selectAll().map { androidVersionEntity: List<AndroidVersionEntity> ->
            androidVersionEntity.toDataObject()
        }
    }

    fun insertAndroidVersion(myAndroidModelData: MyAndroidModelData) {
        androidVersionDao.insert(myAndroidModelData.toRoomObject())
    }

    fun deleteAllAndroidVersion() {
        androidVersionDao.deleteAll()
    }
}

