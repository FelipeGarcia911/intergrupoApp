package felipe.garcia.testapp.base

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Pair
import felipe.garcia.testapp.utils.StringValidationHelper
import felipe.garcia.testapp.utils.errorHandler.ErrorHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class BaseViewModel(context: Context) : ViewModel() {

    var errorHelper: ErrorHelper = ErrorHelper(context)
    val stringHelper = StringValidationHelper()

    private val showProgressDialog = BehaviorSubject.createDefault(Pair<Boolean, String?>(false, null))

    private val showMessage = PublishSubject.create<String>()

    protected fun showMessage(string: String){
        showMessage.onNext(string)
    }

    protected fun showProgressDialog(message: String) {
        showProgressDialog.onNext(Pair(true, message))
    }

    protected fun hideProgressDialog() {
        showProgressDialog.onNext(Pair(false, null))
    }

    fun observableProgressDialog(): Observable<Pair<Boolean, String?>> = showProgressDialog.observeOn(AndroidSchedulers.mainThread())

    fun observableShowMessage(): Observable<String> = showMessage.observeOn(AndroidSchedulers.mainThread())


}