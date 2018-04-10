package com.bajabob.livedata_recyclerview

import android.support.annotation.DrawableRes
import com.xwray.groupie.kotlinandroidextensions.Item

data class ClickableEntryViewModel(
        val name: String,
        @DrawableRes val icon: Int
) : BaseViewModel() {
    override fun getItemFactory(): (BaseViewModel) -> Item {
        return { ClickableItem(it as ClickableEntryViewModel) }
    }

    fun isCooler() : Boolean {
        return !name.startsWith("BMW")
    }
}

