package company.home.intergrupoapp.models

import com.google.gson.annotations.SerializedName

class ProspectModel (@SerializedName("name") var name: String,
                     @SerializedName("lastName") var lastName: String,
                     @SerializedName("identification") var identification: String,
                     @SerializedName("telephone") var telephone: String,
                     @SerializedName("status") var status: Int)