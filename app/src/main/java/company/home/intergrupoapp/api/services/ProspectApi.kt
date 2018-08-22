package company.home.intergrupoapp.api.services

import company.home.intergrupoapp.api.models.ProspectResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ProspectApi {

    @GET("/application/login")
    fun getProspects(): Single<ProspectResponse>

}