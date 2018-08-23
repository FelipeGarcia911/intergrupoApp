package company.home.intergrupoapp.api.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (@SerializedName("token") var token: String)
