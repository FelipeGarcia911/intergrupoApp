package company.home.intergrupoapp.utils

class StringValidationHelper {

    fun isValidEmail(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}