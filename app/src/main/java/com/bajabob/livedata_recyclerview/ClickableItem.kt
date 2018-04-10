package com.bajabob.livedata_recyclerview


import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_clickable_entry.*

class ClickableItem(model: ClickableEntryViewModel) : BaseItem<ClickableEntryViewModel>(model) {

    override fun getLayout() = R.layout.view_holder_clickable_entry

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.icon.setImageResource(model.icon)
        viewHolder.item_name.text = model.name
    }
}
