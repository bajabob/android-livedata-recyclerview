package com.bajabob.livedata_recyclerview

import android.support.annotation.DrawableRes

data class ClickableEntryViewModel(
        val name: String,
        @DrawableRes val icon: Int
) : BaseViewModel() {
    override fun getItemFactory(): (BaseViewModel) -> BaseItem<out Any> {
        return { ClickableItem(it as ClickableEntryViewModel) }
    }

    fun isCooler() : Boolean {
        return !name.startsWith("BMW")
    }
}
