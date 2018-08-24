package company.home.intergrupoapp.api.services

import company.home.intergrupoapp.models.ProspectModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface ProspectApi {

    @GET("/sch/prospects.json")
    fun getProspects(@Header("token") token: String): Single<ArrayList<ProspectModel>>

}