package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MotoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MotoViewModel::class.java)

        viewAdapter = MotoListAdapter(this, viewModel.motoListLiveData())

        recyclerView = findViewById<RecyclerView>(R.id.list).apply {
            adapter = viewAdapter
        }
    }
}
