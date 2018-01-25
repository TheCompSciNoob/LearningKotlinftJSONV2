package com.example.per6.learningkotlinftjsonv2

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by per6 on 1/25/18.
 */
@RealmClass open class Location(var lat : Double = 0.0, var lon : Double = 0.0, @PrimaryKey var id : Long = 0L) : RealmObject() {}