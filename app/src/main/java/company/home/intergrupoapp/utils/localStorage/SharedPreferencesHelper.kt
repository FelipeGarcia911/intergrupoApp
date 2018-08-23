package company.home.intergrupoapp.utils.localStorage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.concurrent.atomic.AtomicBoolean


class SharedPreferencesHelper {

    private val gson: Gson = Gson()
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(activity: Activity): SharedPreferencesHelper {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        return this
    }

    fun write(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun read(key: String): String? {
        return sharedPreferences?.let {  it.getString(key, "")}?.run { "" }
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun saveObject(objects: Any, key: String){
        val objectString = gson.toJson(objects)
        write(key, objectString)
    }

    fun restoreObject(objectClass: Class<*>,key: String): Any? {
        val objectString = read(key)
        return gson.fromJson(objectString, objectClass)
    }

    private object SingletonHolder {
        internal val INSTANCE = SharedPreferencesHelper()
    }

    companion object {
        private lateinit var INSTANCE: SharedPreferencesHelper
        val instance: SharedPreferencesHelper get() = INSTANCE
        private val initialized = AtomicBoolean()

        fun initialize(activity: Activity) {
            if(!initialized.getAndSet(true)) {
                INSTANCE = SharedPreferencesHelper().initialize(activity)
            }
        }

    }

}