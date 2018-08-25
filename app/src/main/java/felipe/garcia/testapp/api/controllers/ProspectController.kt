package felipe.garcia.testapp.api.controllers

import felipe.garcia.testapp.api.Connection
import felipe.garcia.testapp.api.services.ProspectApi
import felipe.garcia.testapp.models.ProspectModel
import felipe.garcia.testapp.utils.localStorage.UserLocalStorage
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