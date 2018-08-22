package company.home.intergrupoapp.api.controllers

import company.home.intergrupoapp.api.models.ProspectResponse
import company.home.intergrupoapp.api.services.ProspectApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProspectController(private val prospectApi: ProspectApi) {

    fun getProspects(): Single<ProspectResponse> {
        return prospectApi.getProspects().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}