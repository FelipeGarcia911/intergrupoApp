package company.home.intergrupoapp.api.controllers

import company.home.intergrupoapp.api.models.LoginResponse
import company.home.intergrupoapp.api.services.LoginApi
import company.home.intergrupoapp.models.USER_KEY
import company.home.intergrupoapp.models.UserModel
import company.home.intergrupoapp.network.Connection
import company.home.intergrupoapp.utils.localStorage.SharedPreferencesHelper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginController {

    private var loginApi: LoginApi = Connection.instance.create(LoginApi::class.java)

    fun signIn(email: String, password:String): Single<LoginResponse> {
        return loginApi.signIn(email, password)
                .map {response -> saveUserData(response, email, password)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun saveUserData(loginResponse: LoginResponse, email: String, password: String): LoginResponse {
        val preferences= SharedPreferencesHelper.instance
        val userObject = UserModel(email, password, loginResponse.authToken)
        preferences.saveObject(userObject, USER_KEY)
        return loginResponse
    }
}