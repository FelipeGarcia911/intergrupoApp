package felipe.garcia.testapp.api.services

import felipe.garcia.testapp.api.models.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("/application/login")
    fun signIn(@Query("email") email: String, @Query("password") password: String): Single<LoginResponse>

}