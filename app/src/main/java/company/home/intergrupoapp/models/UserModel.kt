package company.home.intergrupoapp.models
import com.google.gson.annotations.SerializedName

const val USER_KEY = "user_key"

data class UserModel (@SerializedName("email") var email: String,
                      @SerializedName("password") var password: String,
                      @SerializedName("token") var token: String)