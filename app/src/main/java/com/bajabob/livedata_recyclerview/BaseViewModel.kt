package com.bajabob.livedata_recyclerview

import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_header.*


abstract class BaseViewModel : Group {
    val delegateItem : com.xwray.groupie.kotlinandroidextensions.Item by lazy {
        DelegateItemFactory.getItem(this)
    }

    abstract fun getItemFactory () : (BaseViewModel) -> com.xwray.groupie.kotlinandroidextensions.Item
    
    object DelegateItemFactory {
        fun getItem(me: BaseViewModel) : com.xwray.groupie.kotlinandroidextensions.Item {
            return me.getItemFactory()(me)
        }
    }

    override fun getItemCount(): Int {
        return delegateItem.itemCount
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        delegateItem.unregisterGroupDataObserver(groupDataObserver)
    }

    override fun getItem(position: Int): Item<*> {
        return delegateItem.getItem(position)
    }

    override fun getPosition(item: Item<*>): Int {
        return delegateItem.getPosition(item)
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        return delegateItem.registerGroupDataObserver(groupDataObserver)
    }

    class EmptyItem(val model: String = "EMPTY") : com.xwray.groupie.kotlinandroidextensions.Item() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.header.text = model
        }

        override fun getLayout(): Int {
            return R.layout.view_holder_header
        }
    }

}
