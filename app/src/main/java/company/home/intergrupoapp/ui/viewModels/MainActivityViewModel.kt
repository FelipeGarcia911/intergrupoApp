package company.home.intergrupoapp.ui.viewModels

import company.home.intergrupoapp.utils.localStorage.UserLocalStorage

class MainActivityViewModel {

    private lateinit var userLocalStorage: UserLocalStorage

    fun logoutAction() {
        userLocalStorage = UserLocalStorage()
        userLocalStorage.removeUserData()
    }
}