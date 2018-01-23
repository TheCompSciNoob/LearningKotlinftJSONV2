package com.example.per6.learningkotlinftjsonv2

import android.location.Geocoder
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.map_location_card_view.view.*

/**
 * Created by per6 on 1/23/18.
 */
class LocationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location : Pair<Double, Double>, geocoder: Geocoder) : Unit = with(itemView) {
        val gMapsURL = "http://maps.google.com/maps/api/staticmap?center=${location.first},${location.second}&zoom=15&size=720x360&markers=color:red%7C${location.first},${location.second}&focus=false";
        Glide.with(locationImage.context)
                .load(gMapsURL)
                .into(locationImage)
        latlonTextView.text = "${location.first}, ${location.second}"
        val addresses = geocoder.getFromLocation(location.first, location.second, 1)
        if (addresses.size != 0)
        {
            val address = addresses[0]
            val street = address.getAddressLine(0)
            val city = address.locality
            val state = address.adminArea
            val country = address.countryName
            val postalCode = address.postalCode
            addressTextView.text = "${street}, ${city}, ${state} ${postalCode}, ${country}"
            val stringBuilder = StringBuilder()

        }
    }

}