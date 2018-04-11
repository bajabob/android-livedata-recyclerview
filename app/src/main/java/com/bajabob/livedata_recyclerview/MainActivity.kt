package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MotoViewModel::class.java)
        val groupAdapter = GroupAdapter<ViewHolder>()
        val coolBikes = Section()
        coolBikes.setHeader(HeaderItem("Cool Bikes"))
        coolBikes.setHideWhenEmpty(true)
        val coolerBikes = Section()
        coolerBikes.setHeader(HeaderItem("Cooler Bikes"))
        coolerBikes.setHideWhenEmpty(true)
        groupAdapter.add(coolBikes)
        groupAdapter.add(coolerBikes)
        viewModel.motoListLiveData().observe(this, Observer {
            it?.let {
                coolBikes.update(it.filter{it is PugInABlanket || (it is ClickableEntryViewModel && !it.isCooler())})
                coolerBikes.update(it.filter{(it is ClickableEntryViewModel && it.isCooler())})
            }
        })
        groupAdapter.setOnItemClickListener { item, view -> 
            if (item is PugItem) {
                viewModel.addAPug()
            }
        }
        list.adapter = groupAdapter
    }
}
