package com.bajabob.livedata_recyclerview

import android.support.annotation.DrawableRes

data class ClickableEntryViewModel(
        val name: String,
        @DrawableRes val icon: Int
) 

fun ClickableEntryViewModel.isCool() : Boolean {
    return !name.startsWith("BMW")
}
