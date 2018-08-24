package company.home.intergrupoapp.ui.viewModels

import android.content.Context
import company.home.intergrupoapp.api.controllers.ProspectController
import company.home.intergrupoapp.base.BaseViewModel
import company.home.intergrupoapp.models.ProspectModel
import company.home.intergrupoapp.utils.localStorage.ProspectListLocalStorage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class ProspectListViewModel(context: Context): BaseViewModel(context) {

    private var onProspectList = PublishSubject.create<ArrayList<ProspectModel>>()
    private val prospectController = ProspectController()
    private val listLC = ProspectListLocalStorage()

    private fun getListFromLS() {
        listLC.getList()
    }

    private fun saveListToLS(list: ArrayList<ProspectModel>) {
        listLC.saveList(list)
    }

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
        listLC.getList()?.let { onProspectList.onNext(it) }?: getListFromServer()
    }

    fun onSwipeTop() {
        getListFromServer()
    }

}