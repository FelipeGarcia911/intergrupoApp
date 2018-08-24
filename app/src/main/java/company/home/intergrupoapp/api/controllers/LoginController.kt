package company.home.intergrupoapp.api.controllers

import company.home.intergrupoapp.api.Connection
import company.home.intergrupoapp.api.models.LoginResponse
import company.home.intergrupoapp.api.services.LoginApi
import company.home.intergrupoapp.utils.localStorage.UserLocalStorage
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginController {

    private var loginApi: LoginApi = Connection.instance.create(LoginApi::class.java)

    fun signIn(email: String, password: String): Single<LoginResponse> {
        return loginApi.signIn(email, password)
                .map { response -> saveUserData(response, email, password) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun saveUserData(loginResponse: LoginResponse, email: String, password: String): LoginResponse {
        val userLocalStorage = UserLocalStorage()
        userLocalStorage.saveUser(email, password, loginResponse.authToken)
        return loginResponse
    }
}