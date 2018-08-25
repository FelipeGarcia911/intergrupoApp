package felipe.garcia.testapp.ui.viewModels

import android.content.Context
import felipe.garcia.testapp.base.BaseViewModel
import felipe.garcia.testapp.models.ProspectLogModel
import felipe.garcia.testapp.utils.localStorage.ProspectLogLocalStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class LogFragmentViewModel(context: Context) : BaseViewModel(context) {

    private var onProspectList = PublishSubject.create<ArrayList<ProspectLogModel>>()

    private val listLC = ProspectLogLocalStorage()

    fun onSwipeTop() {
        initListView()
    }

    fun initListView() {
        listLC.getList()?.let { onProspectList.onNext(it) }
    }

    fun onLogList(): Observable<ArrayList<ProspectLogModel>> {
        return onProspectList.observeOn(AndroidSchedulers.mainThread())
    }

}