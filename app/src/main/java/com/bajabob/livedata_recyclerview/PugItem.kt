package com.bajabob.livedata_recyclerview

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.view_holder_pug.*


class PugItem(model: PugInABlanket) : BaseItem<PugInABlanket>(model) {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val pugPath = "http://picsum.photos/200?image=${model.pugNo}"
        val picasso = Picasso.get()
                picasso.setIndicatorsEnabled(true)
                picasso.load(pugPath).into(viewHolder.icon)
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_pug
    }

}