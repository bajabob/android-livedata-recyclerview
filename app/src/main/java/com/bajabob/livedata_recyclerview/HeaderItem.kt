package com.bajabob.livedata_recyclerview

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_header.*

class HeaderItem(val header: String) : Item() {

    override fun getLayout() = R.layout.view_holder_header

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.header.text = header
    }
}
