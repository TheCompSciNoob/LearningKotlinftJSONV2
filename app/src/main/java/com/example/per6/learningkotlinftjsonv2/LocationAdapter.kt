package com.example.per6.learningkotlinftjsonv2

import android.content.Context
import android.location.Geocoder
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.realm.RealmChangeListener
import io.realm.RealmResults
import java.util.*

/**
 * Created by per6 on 1/23/18.
 */
class LocationAdapter(context : Context, val locations : RealmResults<Location>) : RecyclerView.Adapter<LocationViewHolder>(), RealmChangeListener<RealmResults<Location>> {

    private val geocoder = Geocoder(context, Locale.getDefault())

    init {
        locations.addChangeListener(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.map_location_card_view, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder?, position: Int) {
        holder?.bind(locations.get(position)!!, geocoder)
    }

    override fun onChange(t: RealmResults<Location>?) {
        notifyDataSetChanged()
    }
}