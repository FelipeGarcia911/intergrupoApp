package company.home.intergrupoapp.ui.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import company.home.intergrupoapp.network.Connection
import company.home.intergrupoapp.utils.errorHandler.ErrorHelper
import company.home.intergrupoapp.utils.localStorage.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable


open class BaseActivity: AppCompatActivity() {

    lateinit var subscriptions: CompositeDisposable
    lateinit var errorHelper: ErrorHelper
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorHelper = ErrorHelper(this)
        subscriptions = CompositeDisposable()
        initialize()
    }

    private fun initialize(){
        val sharedPreferencesHelper = SharedPreferencesHelper.initialize(this)
        val connection = Connection.initialize()
    }

    override fun onDestroy() {
        subscriptions?.dispose()
        super.onDestroy()
    }

    fun requestPermission(requestPermission: String, requestCode: Int){
        val permission = ContextCompat.checkSelfPermission(this, requestPermission)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(requestPermission), requestCode)
        }
    }

    fun progressDialog(pair: Pair<Boolean, String?>){
        if (pair.first) showAlertDialog("Cargando datos...", pair.second?:"")
        else hideAlertDialog()
    }

    fun showAlertDialog(title: String, message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        dialog = builder.create()
        dialog?.show()
    }

    fun hideAlertDialog(){
        dialog?.dismiss()
    }

}