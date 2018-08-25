package felipe.garcia.testapp.api.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("authToken") var authToken: String,
                         @SerializedName("email") var email: String,
                         @SerializedName("success") var success: Boolean)
