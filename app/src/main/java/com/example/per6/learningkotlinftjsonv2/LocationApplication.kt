package com.example.per6.learningkotlinftjsonv2

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by per6 on 1/25/18.
 */
class LocationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration
                .Builder()
                .name("learningkotlinftjsonv2.realm")
                .deleteRealmIfMigrationNeeded()
                .build())
    }
}