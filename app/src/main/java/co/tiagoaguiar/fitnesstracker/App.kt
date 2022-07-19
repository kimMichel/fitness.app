package co.tiagoaguiar.fitnesstracker

import android.app.Application
import co.tiagoaguiar.fitnesstracker.model.AppDataBase

class App: Application() {

    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = AppDataBase.getDatabase(this)
    }

}