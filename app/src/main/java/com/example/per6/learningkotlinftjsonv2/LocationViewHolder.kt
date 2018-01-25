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

    fun bind(location : Location, geocoder: Geocoder) : Unit = with(itemView) {
        val gMapsURL = "http://maps.google.com/maps/api/staticmap?center=${location.lat},${location.lon}&zoom=17&size=720x360&markers=color:red%7C${location.lat},${location.lon}&focus=false&key=AIzaSyDDU2xOerzBDZkzfY9Wo6FOg6W79xFMriE";
        Glide.with(locationImage.context)
                .load(gMapsURL)
                .into(locationImage)
        latlonTextView.text = "${location.lat}, ${location.lon}"
        val addresses = geocoder.getFromLocation(location.lat, location.lon, 1)
        if (addresses.size != 0)
        {
            val address = addresses[0]
            val street = if (address.getAddressLine(0) != null) "${address.getAddressLine(0)}, " else ""
            val city = if (address.locality != null) "${address.locality}, " else ""
            val state = if (address.adminArea != null) "${address.adminArea}, " else ""
            val postalCode = if (address.postalCode != null) "${address.postalCode}, " else ""
            val country = if (address.countryName != null) address.countryName else ""
            addressTextView.text = StringBuilder()
                    .append(street)
                    .append(city)
                    .append(state)
                    .append(postalCode)
                    .append(country)
                    .toString()
        }
    }

}