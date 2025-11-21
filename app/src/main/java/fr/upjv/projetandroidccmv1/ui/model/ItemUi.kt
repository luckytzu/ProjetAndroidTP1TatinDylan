package fr.upjv.projetandroidccmv1.ui.model

sealed class ItemUi {
    data class Header(val title: String) : ItemUi()
    data class Item(val versionName: String, val versionNumber: String) : ItemUi()
    data class Footer(val count: Int) : ItemUi()
}