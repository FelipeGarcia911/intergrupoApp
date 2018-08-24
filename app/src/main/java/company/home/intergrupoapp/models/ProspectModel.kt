package company.home.intergrupoapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProspectModel(@SerializedName("name") var name: String,
                    @SerializedName("surname") var lastName: String,
                    @SerializedName("schProspectIdentification") var identification: String,
                    @SerializedName("telephone") var telephone: String,
                    @SerializedName("statusCd") var status: Int) : Serializable