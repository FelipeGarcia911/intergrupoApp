package felipe.garcia.testapp.api.controllers

import felipe.garcia.testapp.api.Connection
import felipe.garcia.testapp.api.models.LoginResponse
import felipe.garcia.testapp.api.services.LoginApi
import felipe.garcia.testapp.utils.localStorage.UserLocalStorage
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