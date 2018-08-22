package company.home.intergrupoapp.utils.localStorage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper {

    private var sharedPreferences: SharedPreferences? = null

    fun initialize(activity: Activity) {
        this.sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
    }

    fun write(key: String, value: String) {
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun read(key: String): String {
        return sharedPreferences!!.getString(key, "")
    }

    fun remove(key: String) {
        val editor = sharedPreferences!!.edit()
        editor.remove(key)
        editor.apply()
    }

    private object SingletonHolder {
        internal val INSTANCE = SharedPreferencesHelper()
    }

    companion object {

        val instance: SharedPreferencesHelper
            get() = SingletonHolder.INSTANCE
    }

}