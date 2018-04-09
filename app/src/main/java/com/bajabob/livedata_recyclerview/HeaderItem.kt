package com.bajabob.livedata_recyclerview

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_header.*

class HeaderItem constructor(private val header: HeaderViewModel) : Item() {

    override fun getLayout() = R.layout.view_holder_header

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.header.text = header.name
    }

    override fun equals(other: Any?): Boolean {
        if (other is HeaderItem) {
            return other.header.equals(header)
        }
        return false
    }

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other is HeaderItem) {
            return other.header.equals(header)
        }
        return false
    }

    override fun hashCode(): Int {
        return header.hashCode()
    }
}
