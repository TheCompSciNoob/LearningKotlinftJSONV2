package com.example.per6.learningkotlinftjsonv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //list of locations
        val locations = arrayListOf<Pair<Double, Double>>()

        //RecyclerView
        val locationAdapter = LocationAdapter(this, locations)
        locationRecyclerView.layoutManager = LinearLayoutManager(this)
        locationRecyclerView.adapter = locationAdapter

        //add location to list
        //update adapter
        addButton.setOnClickListener(View.OnClickListener {
            val location = Pair(latitudeInput.text.toString().toDouble(), longitudeInput.text.toString().toDouble())
            locations.add(location)
            locationAdapter.notifyDataSetChanged()
        })
    }
}