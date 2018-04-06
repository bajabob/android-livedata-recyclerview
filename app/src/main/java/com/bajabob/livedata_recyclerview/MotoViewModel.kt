package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class MotoViewModel : ViewModel() {

    /**
     * default dataset
     */
    private val dataset = mutableListOf(
            MainActivity.MotoListItem(MainActivity.MotoListType.HEADER, MainActivity.HeaderViewModel("Cool Bikes")),
            MainActivity.MotoListItem(MainActivity.MotoListType.CLICKABLE_ENTRY, MainActivity.ClickableEntryViewModel("Cafe", R.drawable.dirt_cafe)),
            MainActivity.MotoListItem(MainActivity.MotoListType.CLICKABLE_ENTRY, MainActivity.ClickableEntryViewModel("Cruiser", R.drawable.cruiser)),
            MainActivity.MotoListItem(MainActivity.MotoListType.HEADER, MainActivity.HeaderViewModel("Even Cooler Bikes")),
            MainActivity.MotoListItem(MainActivity.MotoListType.CLICKABLE_ENTRY, MainActivity.ClickableEntryViewModel("BMW GS 1200 (dirt)", R.drawable.gs)),
            MainActivity.MotoListItem(MainActivity.MotoListType.CLICKABLE_ENTRY, MainActivity.ClickableEntryViewModel("BMW GS 1200 (road)", R.drawable.road_gs)),
            MainActivity.MotoListItem(MainActivity.MotoListType.CLICKABLE_ENTRY, MainActivity.ClickableEntryViewModel("Yamaha T7 Concept", R.drawable.t7))
    )

    // mutable in view model
    private val motoListItems = MutableLiveData<List<MainActivity.MotoListItem<Any>>>()

    // immutable outside of view model
    fun motoListLiveData(): LiveData<List<MainActivity.MotoListItem<Any>>> = motoListItems

    init {
        // simulate async data changes over some amount of time (db, remote data source, etc.)
        for (i in 1..50) {
            async {
                delay(i * 1000)
                dataset.add(MainActivity.MotoListItem(MainActivity.MotoListType.CLICKABLE_ENTRY, MainActivity.ClickableEntryViewModel("Yamaha T7 Concept #$i", R.drawable.t7)))
                motoListItems.postValue(dataset)
            }
        }
    }
}