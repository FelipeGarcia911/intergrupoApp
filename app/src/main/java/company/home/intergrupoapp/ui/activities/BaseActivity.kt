package company.home.intergrupoapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import company.home.intergrupoapp.network.Connection
import company.home.intergrupoapp.utils.localStorage.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity: AppCompatActivity() {

    var subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize(){
        val sharedPreferencesHelper = SharedPreferencesHelper.initialize(this)
        val connection = Connection.initialize()
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

}