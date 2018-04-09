package com.bajabob.livedata_recyclerview


import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_clickable_entry.*

class ClickableItem constructor(private val item: ClickableEntryViewModel) : Item() {

    override fun getLayout() = R.layout.view_holder_clickable_entry

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.icon.setImageResource(item.icon)
        viewHolder.item_name.text = item.name
    }

    override fun equals(other: Any?): Boolean {
        if (other is ClickableItem) {
            return item.equals(other.item)
        }
        return false
    }

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other is ClickableItem) {
            return item.equals(other.item)
        }
        return false
    }

    override fun hashCode(): Int {
        return item.hashCode()
    }
}
