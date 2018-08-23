package company.home.intergrupoapp.ui.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import company.home.intergrupoapp.R
import company.home.intergrupoapp.databinding.ActivityLoginBinding
import company.home.intergrupoapp.ui.viewModels.LoginViewModel

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = LoginViewModel(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        subscribe()
        viewModel.onCreate()
    }

    private fun subscribe(){
        subscriptions.addAll(viewModel.onCheckLogin.filter { it }.subscribe { onLoginSuccess() })
    }

    private fun onLoginSuccess(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
