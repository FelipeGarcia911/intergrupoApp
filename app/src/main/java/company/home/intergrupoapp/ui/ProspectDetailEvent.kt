package company.home.intergrupoapp.ui

import com.google.gson.annotations.SerializedName
import company.home.intergrupoapp.models.ProspectModel

const val OPEN_DETAILS = 0

data class ProspectDetailEvent(@SerializedName("prospectModel") val prospectModel: ProspectModel,
                               @SerializedName("eventType") val eventType: Int)