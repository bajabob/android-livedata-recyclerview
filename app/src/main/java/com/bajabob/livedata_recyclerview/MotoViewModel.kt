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
            MotoListItem(MotoListType.HEADER, HeaderViewModel("Cool Bikes")),

            MotoListItem(
                    MotoListType.CLICKABLE_ENTRY,
                    ClickableEntryViewModel("Cafe", R.drawable.dirt_cafe)
            ),
            MotoListItem(
                    MotoListType.CLICKABLE_ENTRY,
                    ClickableEntryViewModel("Cruiser", R.drawable.cruiser)
            ),

            MotoListItem(MotoListType.HEADER, HeaderViewModel("Even Cooler Bikes")),

            MotoListItem(
                    MotoListType.CLICKABLE_ENTRY,
                    ClickableEntryViewModel("BMW GS 1200 (dirt)", R.drawable.gs)
            ),
            MotoListItem(
                    MotoListType.CLICKABLE_ENTRY,
                    ClickableEntryViewModel("BMW GS 1200 (road)", R.drawable.road_gs)
            ),
            MotoListItem(
                    MotoListType.CLICKABLE_ENTRY,
                    ClickableEntryViewModel("Yamaha T7 Concept", R.drawable.t7)
            )
    )

    // mutable in view model
    private val motoListItems = MutableLiveData<List<MotoListItem<Any>>>()

    // immutable outside of view model
    fun motoListLiveData(): LiveData<List<MotoListItem<Any>>> = motoListItems

    init {
        // simulate async data changes over some amount of time (db, remote data source, etc.)
        for (i in 1..50) {
            async {
                delay(i * 1000)
                dataset.add( if(i%2==0)i else dataset.size - i,
                        MotoListItem(MotoListType.CLICKABLE_ENTRY,
                        ClickableEntryViewModel("Yamaha T7 Concept #$i", R.drawable.t7))
                )
                motoListItems.postValue(dataset)
            }
        }
    }
}