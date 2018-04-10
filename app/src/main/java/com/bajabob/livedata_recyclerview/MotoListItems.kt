package com.bajabob.livedata_recyclerview

import android.support.annotation.DrawableRes

data class ClickableEntryViewModel(
        val name: String,
        @DrawableRes val icon: Int
) : ShitBalls() {
    fun isCooler() : Boolean {
        return !name.startsWith("BMW")
    }
}

