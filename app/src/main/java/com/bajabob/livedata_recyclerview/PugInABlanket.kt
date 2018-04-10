package com.bajabob.livedata_recyclerview

import com.xwray.groupie.kotlinandroidextensions.Item


data class PugInABlanket(val pugNo: Int) : BaseViewModel() {
    override fun getItemFactory(): (BaseViewModel) -> Item {
        return { PugItem(it as PugInABlanket)}
    }

}