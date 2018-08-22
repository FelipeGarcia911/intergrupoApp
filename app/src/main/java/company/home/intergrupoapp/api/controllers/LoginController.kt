package company.home.intergrupoapp.api.controllers

import company.home.intergrupoapp.api.models.LoginResponse
import company.home.intergrupoapp.api.services.LoginApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginController(private val loginApi: LoginApi) {

    fun signIn(email: String, password:String): Single<LoginResponse> {
        return loginApi.signIn(email, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}