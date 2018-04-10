package com.bajabob.livedata_recyclerview

import com.xwray.groupie.kotlinandroidextensions.Item

abstract class BaseItem<T:Any> constructor(protected val model: T) : Item(model.hashCode().toLong()) {
    override fun equals(other: Any?): Boolean {
        return if (other is Item) isSameAs(other) else false
    }

    override fun hashCode(): Int {
        return model.hashCode()
    }
}
