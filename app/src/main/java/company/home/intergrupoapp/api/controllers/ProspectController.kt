package company.home.intergrupoapp.api.controllers

import company.home.intergrupoapp.api.models.ProspectResponse
import company.home.intergrupoapp.api.services.ProspectApi
import company.home.intergrupoapp.network.Connection
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProspectController {

    private var prospectApi = Connection.instance.create(ProspectApi::class.java)

    fun getProspects(): Single<ProspectResponse> {
        return prospectApi.getProspects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}