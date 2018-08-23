package company.home.intergrupoapp.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import company.home.intergrupoapp.utils.URL_SERVER
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicBoolean

class Connection {

    companion object {
        private lateinit var INSTANCE: Retrofit
        val instance: Retrofit get() = INSTANCE
        private val initialized = AtomicBoolean()

        fun initialize() {
            if (!initialized.getAndSet(true)) {
                INSTANCE = create()
            }
        }

        fun create(): Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(URL_SERVER)
                    .client(OkHttpClient())
                    .build()

        }

    }
}