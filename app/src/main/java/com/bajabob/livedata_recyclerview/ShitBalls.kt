package com.bajabob.livedata_recyclerview

import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_header.*


open class ShitBalls : Group {
    val delegateItem : BaseItem<out Any> by lazy {
        DelegateItemFactory.getItem(this)
    }

    object DelegateItemFactory {
        fun getItem(me: Any) : BaseItem<out Any> {
            when (me) {
                is ClickableEntryViewModel ->
                    return ClickableItem(me)
            }
            return EmptyItem()
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

    class EmptyItem : BaseItem<String>("EMPTY") {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.header.text = model
        }

        override fun getLayout(): Int {
            return R.layout.view_holder_header
        }
    }

}
