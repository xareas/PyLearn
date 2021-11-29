package com.xareas.pylearn

import android.app.Application
import com.xareas.pylearn.data.db.PyLearnDatabase

class PyLearnApplication : Application() {


    companion object {
        lateinit var pyLearnDatabase: PyLearnDatabase
        lateinit var instance: PyLearnApplication private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        pyLearnDatabase =  PyLearnDatabase.buildDatabase(this)
    }

}