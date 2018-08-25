package felipe.garcia.testapp.ui.viewModels

import android.content.Context
import android.databinding.ObservableField
import felipe.garcia.testapp.api.controllers.LoginController
import felipe.garcia.testapp.base.BaseViewModel
import felipe.garcia.testapp.utils.localStorage.UserLocalStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class LoginViewModel(context: Context) : BaseViewModel(context) {

    var email = ObservableField<String>()
    var password = ObservableField<String>()

    var emailError = ObservableField<String>()
    var passwordError = ObservableField<String>()

    private var onCheckLogin = BehaviorSubject.createDefault(false)
    private var loginController = LoginController()

    fun onCreate() {
        val userLocalStorage = UserLocalStorage()
        onCheckLogin.onNext(userLocalStorage.getUser()?.token?.isNotEmpty() ?: false)
    }

    fun onClickLoginButton() {
        clearErrors()
        val emailString = email.get() ?: ""
        val passwordString = password.get() ?: ""
        when {
            emailString.isEmpty() -> emailError.set("Completar el email")
            !stringHelper.isValidEmail(emailString) -> emailError.set("Email inválido")
            passwordString.isEmpty() -> passwordError.set("Completar el password")
            else -> doLogin(emailString, passwordString)
        }
    }

    private fun doLogin(email: String, password: String) {
        loginController.signIn(email, password)
                .doOnSubscribe { showProgressDialog("Iniciando sesion...") }
                .doFinally(this::hideProgressDialog)
                .subscribe({ onCheckLogin.onNext(true) }, this::onLoginError)
    }

    private fun onLoginError(throwable: Throwable) {
        errorHelper.showConnectionError(throwable)
    }

    private fun clearErrors() {
        emailError.set("")
        passwordError.set("")
    }

    fun onCheckLogin(): Observable<Boolean> = onCheckLogin.observeOn(AndroidSchedulers.mainThread())

}