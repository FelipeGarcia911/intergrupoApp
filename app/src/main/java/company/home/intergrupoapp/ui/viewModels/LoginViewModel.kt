package company.home.intergrupoapp.ui.viewModels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import company.home.intergrupoapp.api.controllers.LoginController
import company.home.intergrupoapp.models.USER_KEY
import company.home.intergrupoapp.models.UserModel
import company.home.intergrupoapp.utils.ErrorHelper
import company.home.intergrupoapp.utils.StringValidationHelper
import company.home.intergrupoapp.utils.localStorage.SharedPreferencesHelper
import io.reactivex.subjects.PublishSubject

class LoginViewModel(context: Context): ViewModel() {

    var email = ObservableField<String>()
    var password = ObservableField<String>()

    private val preferences= SharedPreferencesHelper.instance
    var onCheckLogin = PublishSubject.create<Boolean>()

    private val errorHelper = ErrorHelper(context)
    private val stringHelper = StringValidationHelper()
    private var loginController = LoginController()

    fun onCreate() {
        val userModel = preferences.restoreObject(UserModel::class.java, USER_KEY) as UserModel?
        onCheckLogin.onNext(userModel?.let { it.token.isNotEmpty() }?: false)
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
        loginController.signIn(email, password).subscribe({onCheckLogin.onNext(true)}, this::onLoginError)
    }

    private fun onLoginError(throwable: Throwable) {
        errorHelper.showMessage("Error iniciando sesion: ".plus(throwable.message))
    }

}