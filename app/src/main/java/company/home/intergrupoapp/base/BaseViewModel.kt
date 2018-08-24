package company.home.intergrupoapp.base

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Pair
import company.home.intergrupoapp.utils.errorHandler.ErrorHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

open class BaseViewModel(context: Context) : ViewModel() {

    var errorHelper: ErrorHelper = ErrorHelper(context)

    private val showProgressDialog = BehaviorSubject.createDefault(Pair<Boolean, String?>(false, null))

    protected fun showProgressDialog(message: String) {
        showProgressDialog.onNext(Pair(true, message))
    }

    protected fun hideProgressDialog() {
        showProgressDialog.onNext(Pair(false, null))
    }

    fun observableProgressDialog(): Observable<Pair<Boolean, String?>> = showProgressDialog.observeOn(AndroidSchedulers.mainThread())

}