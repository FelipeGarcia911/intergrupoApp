package company.home.intergrupoapp.utils

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    errorMessage?.let { view.error = it }
}