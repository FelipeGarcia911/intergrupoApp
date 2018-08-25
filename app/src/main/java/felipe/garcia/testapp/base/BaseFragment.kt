package felipe.garcia.testapp.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Pair
import android.widget.Toast
import felipe.garcia.testapp.ui.OnClickListener
import felipe.garcia.testapp.utils.eventBus.GreenRobotEventBus
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment(), OnClickListener {

    lateinit var eventBus: GreenRobotEventBus
    lateinit var subscriptions: CompositeDisposable
    private var dialog: AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        subscriptions = CompositeDisposable()
        eventBus = GreenRobotEventBus.instance
    }

    override fun onDetach() {
        super.onDetach()
        subscriptions.dispose()
    }

    override fun onClick(item: Any) {}

    fun progressDialog(pair: Pair<Boolean, String?>) {
        if (pair.first) showAlertDialog("Cargando datos...", pair.second ?: "")
        else hideAlertDialog()
    }

    fun showAlertDialog(title: String, message: String) {
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
            builder.setMessage(message)
            dialog = builder.create()
            dialog?.show()
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun hideAlertDialog() {
        dialog?.dismiss()
    }

}