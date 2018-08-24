package company.home.intergrupoapp.ui.viewModels

import android.content.Context
import company.home.intergrupoapp.api.controllers.ProspectController
import company.home.intergrupoapp.models.ProspectModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class ProspectListViewModel(context: Context): BaseViewModel(context) {

    private var onProspectList = PublishSubject.create<ArrayList<ProspectModel>>()
    private val prospectController = ProspectController()

    private fun getListFromLS() {}

    private fun saveListToLS(list: ArrayList<ProspectModel>) {}

    private fun getListFromServer() {
        prospectController.getProspects()
                .doOnSubscribe { showProgressDialog("Cargando prospectos del servidor...") }
                .doFinally(this::hideProgressDialog)
                .subscribe(this::onGetListFromServerSuccess, this::onGetListFromServerFailure)
    }

    private fun onGetListFromServerSuccess(list: ArrayList<ProspectModel>) {
        onProspectList.onNext(list)
        saveListToLS(list)
    }

    private fun onGetListFromServerFailure(throwable: Throwable) {
        errorHelper.showConnectionError(throwable)
    }

    fun onProspectList(): Observable<ArrayList<ProspectModel>> {
        return onProspectList.observeOn(AndroidSchedulers.mainThread())
    }

    fun initListView() {
        getListFromServer()
    }

    fun onSwipeTop() {
        getListFromServer()
    }

}