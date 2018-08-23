package company.home.intergrupoapp.ui.viewModels

import android.content.Context
import android.databinding.ObservableField
import company.home.intergrupoapp.api.controllers.LoginController
import company.home.intergrupoapp.models.USER_KEY
import company.home.intergrupoapp.models.UserModel
import company.home.intergrupoapp.utils.StringValidationHelper
import company.home.intergrupoapp.utils.localStorage.SharedPreferencesHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class LoginViewModel(context: Context): BaseViewModel(context) {

    var email = ObservableField<String>()
    var password = ObservableField<String>()

    private val preferences= SharedPreferencesHelper.instance
    private var onCheckLogin = BehaviorSubject.createDefault(false)

    private val stringHelper = StringValidationHelper()
    private var loginController = LoginController()

    fun onCreate() {
        val userModel = preferences.restoreObject(UserModel::class.java, USER_KEY) as UserModel?
        onCheckLogin.onNext(userModel?.let { it.token.isNotEmpty() }?: false)
        email.set("directo@directo.com")
        password.set("directo123")
    }

    fun onClickLoginButton(){
        val emailString = email.get()?:""
        val passwordString = password.get()?:""
        when {
            emailString.isEmpty() -> errorHelper.showMessage("Completar el email")
            !stringHelper.isValidEmail(emailString) -> errorHelper.showMessage("Email inválido")
            passwordString.isEmpty() -> errorHelper.showMessage("Completar el password")
            else -> doLogin(emailString, passwordString)
        }
    }

    private fun doLogin(email: String, password:String){
        loginController.signIn(email, password)
                .doOnSubscribe{showProgressDialog("Iniciando sesion...")}
                .doFinally(this::hideProgressDialog)
                .subscribe({onCheckLogin.onNext(true)}, this::onLoginError)

    }

    private fun onLoginError(throwable: Throwable) {
        errorHelper.showConnectionError(throwable)
    }

    fun onCheckLogin(): Observable<Boolean> = onCheckLogin.observeOn(AndroidSchedulers.mainThread())

}