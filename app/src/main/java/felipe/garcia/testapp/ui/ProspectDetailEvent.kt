package felipe.garcia.testapp.ui

import com.google.gson.annotations.SerializedName
import felipe.garcia.testapp.models.ProspectModel

const val OPEN_DETAILS = 0

data class ProspectDetailEvent(@SerializedName("prospectModel") val prospectModel: ProspectModel,
                               @SerializedName("eventType") val eventType: Int)