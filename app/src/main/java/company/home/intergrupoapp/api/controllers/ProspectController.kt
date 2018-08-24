package company.home.intergrupoapp.api.controllers

import company.home.intergrupoapp.api.Connection
import company.home.intergrupoapp.api.services.ProspectApi
import company.home.intergrupoapp.models.ProspectModel
import company.home.intergrupoapp.utils.localStorage.UserLocalStorage
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProspectController {

    private val prospectApi = Connection.instance.create(ProspectApi::class.java)

    fun getProspects(): Single<ArrayList<ProspectModel>> {
        return prospectApi.getProspects(getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getToken(): String {
        val userLocalStorage = UserLocalStorage()
        return userLocalStorage.getToken()
    }

}