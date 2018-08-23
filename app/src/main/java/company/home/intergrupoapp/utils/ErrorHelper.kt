package company.home.intergrupoapp.utils

import android.content.Context
import android.widget.Toast

class ErrorHelper(val context: Context) {

    fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}