package company.home.intergrupoapp.ui.viewModels

import company.home.intergrupoapp.utils.localStorage.ProspectListLocalStorage
import company.home.intergrupoapp.utils.localStorage.UserLocalStorage

class MainActivityViewModel {

    private lateinit var userLocalStorage: UserLocalStorage
    private lateinit var prospectLocalStorage: ProspectListLocalStorage

    fun logoutAction() {
        userLocalStorage = UserLocalStorage()
        userLocalStorage.removeUserData()

        prospectLocalStorage = ProspectListLocalStorage()
        prospectLocalStorage.removeList()
    }
}