package com.bajabob.livedata_recyclerview


data class PugInABlanket(val pugNo: Int) : BaseViewModel() {
    override fun getItemFactory(): (BaseViewModel) -> BaseItem<out Any> {
        return { PugItem(it as PugInABlanket)}
    }

}