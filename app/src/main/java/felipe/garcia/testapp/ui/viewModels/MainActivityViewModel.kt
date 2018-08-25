package felipe.garcia.testapp.ui.viewModels

import felipe.garcia.testapp.utils.localStorage.ProspectListLocalStorage
import felipe.garcia.testapp.utils.localStorage.UserLocalStorage

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