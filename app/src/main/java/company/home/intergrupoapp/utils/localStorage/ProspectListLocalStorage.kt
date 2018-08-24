package company.home.intergrupoapp.utils.localStorage

import company.home.intergrupoapp.models.ProspectModel

class ProspectListLocalStorage {

    val LIST_KEY = "list_key"

    private val sharedPreferencesHelper = SharedPreferencesHelper.instance

    fun getList(): ArrayList<ProspectModel>? {
        return sharedPreferencesHelper.restoreList(ProspectModel::class, LIST_KEY) as ArrayList<ProspectModel>?
    }

    fun saveList(list: ArrayList<ProspectModel>) {
        sharedPreferencesHelper.saveObject(list, LIST_KEY)
    }

    fun removeList() {
        sharedPreferencesHelper.remove(LIST_KEY)
    }

}