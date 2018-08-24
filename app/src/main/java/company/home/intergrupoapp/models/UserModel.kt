package company.home.intergrupoapp.models

import com.google.gson.annotations.SerializedName

data class UserModel(@SerializedName("email") var email: String,
                     @SerializedName("password") var password: String,
                     @SerializedName("token") var token: String)