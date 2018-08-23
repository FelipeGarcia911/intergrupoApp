package company.home.intergrupoapp.utils.errorHandler

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

const val CONNECTION_ERROR = "Error de conexion"

class ErrorHelper(val context: Context) {

    fun showConnectionError(throwable: Throwable){
        val errorMessage = getMessageError(throwable)
        showMessage("Error: ".plus(errorMessage))
    }

    fun showMessage(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun getMessageError(e: Throwable): String {
        e.printStackTrace()
        var error = "Server Error"
        try {
            error = e.message.toString()
            if (e is HttpException) {
                val responseBody = e.response().errorBody()?.string()
                val errorResponse = Gson().fromJson(responseBody, Error::class.java)
                error = errorResponse.error?:"-"
            } else if (e is IOException) {
                error = CONNECTION_ERROR
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
        return error
    }

}