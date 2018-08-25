package felipe.garcia.testapp.models

import com.google.gson.annotations.SerializedName

data class ProspectLogModel(@SerializedName("identification") var identification: String,
                            @SerializedName("name") var name: String,
                            @SerializedName("lastName") var lastName: String,
                            @SerializedName("telephone") var telephone: String,
                            @SerializedName("date") var date: String)