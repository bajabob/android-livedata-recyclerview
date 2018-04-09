package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MotoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MotoViewModel::class.java)
        val groupAdapter = GroupAdapter<ViewHolder>()
        viewAdapter = groupAdapter
        val bikes = Section()
        groupAdapter.add(bikes)
        viewModel.motoListLiveData().observe(this, Observer {
            val update = mutableListOf<Group>()
            it?.map {
                when (it.view) {
                    MotoListType.CLICKABLE_ENTRY ->
                        update.add(ClickableItem(it.data as ClickableEntryViewModel))
                    MotoListType.HEADER ->
                    update.add(HeaderItem(it.data as HeaderViewModel))
                }
            }
            /*
            bikes.update(it?.map {
                when (it.view) {
                    MotoListType.HEADER ->
                        HeaderItem(R.string.search_menu_title)
                    MotoListType.CLICKABLE_ENTRY ->
                        HeaderItem(R.string.search_menu_title)
                }
            }?:listOf<Group>())
            */
            bikes.update(update)
        })


        viewAdapter = groupAdapter

        recyclerView = findViewById<RecyclerView>(R.id.list).apply {
            adapter = viewAdapter
        }
    }
}
