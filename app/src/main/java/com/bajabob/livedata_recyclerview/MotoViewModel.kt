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
            ClickableEntryViewModel("Cafe", R.drawable.dirt_cafe),
            ClickableEntryViewModel("Cruiser", R.drawable.cruiser),
            ClickableEntryViewModel("BMW GS 1200 (dirt)", R.drawable.gs),
            ClickableEntryViewModel("BMW GS 1200 (road)", R.drawable.road_gs),
            ClickableEntryViewModel("Yamaha T7 Concept", R.drawable.t7)
    )

    // mutable in view model
    private val motoListItems = MutableLiveData<List<ClickableEntryViewModel>>()

    // immutable outside of view model
    fun motoListLiveData(): LiveData<List<ClickableEntryViewModel>> = motoListItems

    init {
        // simulate async data changes over some amount of time (db, remote data source, etc.)
        for (i in 1..50) {
            async {
                delay(i * 1000)
                dataset.add( if(i%2==0)i else dataset.size - i,
                        ClickableEntryViewModel("Yamaha T7 Concept #$i", R.drawable.t7)
                )
                motoListItems.postValue(dataset)
            }
        }
    }
}