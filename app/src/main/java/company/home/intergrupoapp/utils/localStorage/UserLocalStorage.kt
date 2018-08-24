package company.home.intergrupoapp.utils.localStorage

import company.home.intergrupoapp.models.UserModel

class UserLocalStorage {

    val USER_KEY = "user_key"
    private val sharedPreferencesHelper = SharedPreferencesHelper.instance

    fun getToken(): String {
        var token = ""
        val userModel = getUser()
        userModel?.let {
            token = it.token
        }
        return token
    }

    fun saveUser(email: String, password: String, token: String) {
        val userObject = UserModel(email, password, token)
        sharedPreferencesHelper.saveObject(userObject, USER_KEY)
    }

    fun getUser(): UserModel? {
        return sharedPreferencesHelper.restoreObject(UserModel::class.java, USER_KEY) as UserModel?
    }

    fun removeUserData() {
        sharedPreferencesHelper.remove(USER_KEY)
    }

}