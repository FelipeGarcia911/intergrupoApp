package company.home.intergrupoapp.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import company.home.intergrupoapp.api.Connection
import company.home.intergrupoapp.utils.errorHandler.ErrorHelper
import company.home.intergrupoapp.utils.eventBus.GreenRobotEventBus
import company.home.intergrupoapp.utils.localStorage.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable


open class BaseActivity : AppCompatActivity() {

    lateinit var errorHelper: ErrorHelper
    lateinit var subscriptions: CompositeDisposable
    lateinit var eventBus: GreenRobotEventBus
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeSingleton()
        initialize()
    }

    fun registerEventBus() {
        eventBus?.register(this)
    }

    fun unregisterEventBus() {
        eventBus?.unregister(this)
    }

    private fun initialize() {
        errorHelper = ErrorHelper(this)
        subscriptions = CompositeDisposable()
        eventBus = GreenRobotEventBus.instance
    }

    private fun initializeSingleton() {
        SharedPreferencesHelper.initialize(this)
        GreenRobotEventBus.initialize()
        Connection.initialize()
    }

    override fun onDestroy() {
        subscriptions?.dispose()
        super.onDestroy()
    }

    fun requestPermission(requestPermission: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this, requestPermission)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(requestPermission), requestCode)
        }
    }

    fun progressDialog(pair: Pair<Boolean, String?>) {
        if (pair.first) showAlertDialog("Cargando datos...", pair.second ?: "")
        else hideAlertDialog()
    }

    fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        dialog = builder.create()
        dialog?.show()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun hideAlertDialog() {
        dialog?.dismiss()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun setToolbarTitle(string: String) {
        title = string
    }

}