package felipe.garcia.testapp.api.services

import felipe.garcia.testapp.models.ProspectModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface ProspectApi {

    @GET("/sch/prospects.json")
    fun getProspects(@Header("token") token: String): Single<ArrayList<ProspectModel>>

}