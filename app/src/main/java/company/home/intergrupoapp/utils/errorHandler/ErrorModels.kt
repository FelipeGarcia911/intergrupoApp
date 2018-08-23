package company.home.intergrupoapp.utils.errorHandler

import com.google.gson.annotations.SerializedName

class Error(@SerializedName("error") val error: String? = null,
            @SerializedName("code") val code: Any? = null)
