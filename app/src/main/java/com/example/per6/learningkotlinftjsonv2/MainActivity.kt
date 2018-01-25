package com.example.per6.learningkotlinftjsonv2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    private val lastInputIdKey: String = "last input id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shared preferences
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val lastInputId = preferences.getLong(lastInputIdKey, 0)

        //database
        realm = Realm.getDefaultInstance()
        val results = realm.where(Location::class.java).findAllAsync()
        val userInputLocation = realm.where(Location::class.java).equalTo("id", lastInputId).findFirst()
        if (userInputLocation == null) {
            latitudeInput.setText("")
            longitudeInput.setText("")
        } else {
            latitudeInput.setText("${userInputLocation.lat}")
            longitudeInput.setText("${userInputLocation.lon}")
        }

        //RecyclerView
        val locationAdapter = LocationAdapter(this, results)
        locationRecyclerView.layoutManager = LinearLayoutManager(this)
        locationRecyclerView.adapter = locationAdapter

        //add location to list
        //update adapter
        addButton.setOnClickListener(View.OnClickListener {
            val location = Location(latitudeInput.text.toString().toDouble(), longitudeInput.text.toString().toDouble(), System.currentTimeMillis())
            if (location.lat < -90 || location.lat > 90 || location.lon < -180 || location.lon > 180) {
                Toast.makeText(this, "Invalid latitude or longitude", Toast.LENGTH_SHORT).show()
            } else {
                //save id
                val editor = preferences.edit()
                editor.putLong(lastInputIdKey, location.id)
                editor.apply()
                //save in database
                realm.beginTransaction()
                realm.copyToRealm(location)
                realm.commitTransaction()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.removeAllChangeListeners()
        realm.close()
    }
}
