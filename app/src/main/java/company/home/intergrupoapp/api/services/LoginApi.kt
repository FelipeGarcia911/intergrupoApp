package company.home.intergrupoapp.api.services

import company.home.intergrupoapp.api.models.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("/application/login")
    fun signIn(@Query("email") email: String, @Query("password") password: String): Single<LoginResponse>

}