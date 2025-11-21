package fr.upjv.projetandroidccmv1.data.mapping

import fr.upjv.projetandroidccmv1.data.local.model.AndroidVersionEntity
import fr.upjv.projetandroidccmv1.data.model.MyAndroidModelData

fun MyAndroidModelData.toRoomObject(): AndroidVersionEntity {
    return AndroidVersionEntity(
        name = versionName,
        code = versionCode,
        use = versionUse,
        releaseDate = versionReleaseData,
        apiLevel = versionApiLevel
    )
}

fun List<AndroidVersionEntity>.toDataObject(): List<MyAndroidModelData> {
    return this.map { eachItem ->
        MyAndroidModelData(
            versionName = eachItem.name,
            versionCode = eachItem.code,
            versionUse = eachItem.use,
            versionReleaseData = eachItem.releaseDate,
            versionApiLevel = eachItem.apiLevel
        )
    }
}

