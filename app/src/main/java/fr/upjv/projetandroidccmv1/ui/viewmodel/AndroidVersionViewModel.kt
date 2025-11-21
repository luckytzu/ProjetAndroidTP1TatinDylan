package fr.upjv.projetandroidccmv1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.upjv.projetandroidccmv1.data.model.MyAndroidModelData
import fr.upjv.projetandroidccmv1.data.repository.AndroidVersionRepository
import fr.upjv.projetandroidccmv1.ui.model.ItemUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

class AndroidVersionViewModel : ViewModel() {

    private val androidVersionRepository: AndroidVersionRepository by lazy {
        AndroidVersionRepository()
    }

    val androidVersionList: StateFlow<List<ItemUi>> =
        androidVersionRepository.selectAllAndroidVersion()
            .map { androidObjectEntities: List<MyAndroidModelData> ->
                androidObjectEntities
                    .groupBy { androidModelData -> androidModelData.versionName }
                    .flatMap { (versionName, itemsOfGroup) ->
                        buildList {
                            add(ItemUi.Header(title = versionName))
                            addAll(
                                itemsOfGroup.map { each ->
                                    ItemUi.Item(
                                        versionName = each.versionName,
                                        versionNumber = each.versionCode,
                                        versionUse = each.versionUse,
                                        versionReleaseData = each.versionReleaseData,
                                        versionApiLevel = each.versionApiLevel
                                    )
                                }
                            )
                        }
                    }
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.Lazily
            )

    fun insertAndroidVersion() {
        viewModelScope.launch(Dispatchers.IO) {

            val random = Random.nextInt(1, 15)
            val randomVersion = Random.nextInt(0, 9)
            val randomApi = Random.nextInt(1, 30)

            val newItem = MyAndroidModelData(
                versionName = "Android $random",
                versionCode = "$random.$randomVersion",
                versionUse = random % 2 == 0,
                versionReleaseData = "20${10 + randomVersion}-01-01",
                versionApiLevel = randomApi
            )

            androidVersionRepository.insertAndroidVersion(newItem)
        }
    }

    fun deleteAllAndroidVersion() {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.deleteAllAndroidVersion()
        }
    }
}
