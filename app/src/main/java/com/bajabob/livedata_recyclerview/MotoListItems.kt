package com.bajabob.livedata_recyclerview

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import com.xwray.groupie.Group

/**
 * different types of data our list can represent
 */
enum class MotoListType(
        // the layout to inflate
        @LayoutRes val layoutId: Int,
        // given a view, creates a new instance of Bindable
        val createNewViewHolder: (Any) -> Group
) {
    HEADER(R.layout.view_holder_header, { v -> HeaderItem(v as HeaderViewModel)}),
    CLICKABLE_ENTRY(R.layout.view_holder_clickable_entry, { v -> ClickableItem(v as ClickableEntryViewModel)})
}

/**
 * represents one item in the RV (separates view from data)
 */
data class MotoListItem<out Any>(
        val view: MotoListType,
        val data: Any
)

/**
 * ViewModels -> data as received from single source of truth (db, or remote). Should only
 *  contain data that maps to views.
 */

data class HeaderViewModel(
        val name: String
)

data class ClickableEntryViewModel(
        val name: String,
        @DrawableRes val icon: Int
)
