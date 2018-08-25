package felipe.garcia.testapp.utils.localStorage

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KClass


const val PREFERENCES_NAME = "SHARED_PREFERENCES"

class SharedPreferencesHelper {

    private val gson: Gson = Gson()
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(activity: Activity): SharedPreferencesHelper {
        sharedPreferences = activity.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        return this
    }

    private fun write(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun read(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun saveObject(objects: Any, key: String) {
        val objectString = gson.toJson(objects)
        write(key, objectString)
    }

    fun restoreObject(objectClass: Class<*>, key: String): Any? {
        val objectString = read(key)
        return gson.fromJson(objectString, objectClass)
    }

    fun <T : Any> restoreList(kClass: KClass<T>, key: String): Any? {
        val jsonString = read(key)
        return jsonString.let {
            gson.fromJson<List<T>>(it, ListOfSomething(kClass.java))
        } ?: null
    }

    internal class ListOfSomething<X>(wrapped: Class<X>) : ParameterizedType {

        private val wrapped: Class<*>

        init {
            this.wrapped = wrapped
        }

        override fun getActualTypeArguments(): Array<Type> {
            return arrayOf(wrapped)
        }

        override fun getRawType(): Type {
            return ArrayList::class.java
        }

        override fun getOwnerType(): Type? {
            return null
        }
    }

    companion object {
        private lateinit var INSTANCE: SharedPreferencesHelper
        val instance: SharedPreferencesHelper get() = INSTANCE
        private val initialized = AtomicBoolean()

        fun initialize(activity: Activity) {
            if (!initialized.getAndSet(true)) {
                INSTANCE = SharedPreferencesHelper().initialize(activity)
            }
        }

    }

}