package company.home.intergrupoapp.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import company.home.intergrupoapp.utils.URL_SERVER
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicBoolean

const val CONTENT_TYPE = "Content-Type"
const val APPLICATION_JSON = "application/json"

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

        private fun create(): Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(URL_SERVER)
                    .client(getHttpClient())
                    .build()
        }

        private fun getHttpClient(): OkHttpClient {
            val headerInterceptor = getHeaderInterceptor()
            val loggingInterceptor = getLogginInterceptor()
            return OkHttpClient
                    .Builder()
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
        }

        private fun getLogginInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return loggingInterceptor
        }

        private fun getHeaderInterceptor(): Interceptor {
            return Interceptor { chain ->
                val newBuilder = chain.request()
                        .newBuilder()
                        .method(chain.request().method(), chain.request().body())

                var request = chain.request()
                newBuilder.header(CONTENT_TYPE, APPLICATION_JSON)
                request = newBuilder.build()
                chain.proceed(request)
            }
        }
    }
}