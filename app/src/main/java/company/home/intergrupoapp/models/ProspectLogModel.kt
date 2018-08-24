package company.home.intergrupoapp.models

import com.google.gson.annotations.SerializedName

data class ProspectLogModel(@SerializedName("name") var name: String,
                            @SerializedName("lastName") var lastName: String,
                            @SerializedName("telephone") var telephone: String,
                            @SerializedName("date") var date: String)