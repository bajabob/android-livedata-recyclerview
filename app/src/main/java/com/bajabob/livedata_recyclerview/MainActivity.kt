package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xwray.groupie.Group
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
        val bikes = Section()
        groupAdapter.add(bikes)
        viewModel.motoListLiveData().observe(this, Observer {
            bikes.update( it?.map {it.getView()} ?: listOf<Group>())
        })

        list.adapter = groupAdapter
    }
}
