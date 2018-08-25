package felipe.garcia.testapp.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import felipe.garcia.testapp.R
import felipe.garcia.testapp.base.BaseActivity
import felipe.garcia.testapp.databinding.ActivityLoginBinding
import felipe.garcia.testapp.ui.viewModels.LoginViewModel
import felipe.garcia.testapp.utils.EXTERNAL_STORAGE_PERMISSION

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = LoginViewModel(this)
        binding.viewModel = viewModel

        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_STORAGE_PERMISSION)

        subscribe()
        viewModel.onCreate()
    }

    private fun subscribe() {
        subscriptions.addAll(
                viewModel.onCheckLogin().filter { it }.subscribe { onLoginSuccess() },
                viewModel.observableProgressDialog().subscribe(this::progressDialog)
        )
    }

    private fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == EXTERNAL_STORAGE_PERMISSION) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                errorHelper.showMessage("Permiso denegado, no se guardaran su datos de sesion")
            }
        }
    }
}
