package felipe.garcia.testapp.utils

class StringValidationHelper {

    fun isValidEmail(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}