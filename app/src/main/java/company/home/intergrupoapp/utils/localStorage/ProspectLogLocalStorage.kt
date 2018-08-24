package company.home.intergrupoapp.utils.localStorage

import company.home.intergrupoapp.models.ProspectLogModel


class ProspectLogLocalStorage {

    val LOG_LIST_KEY = "log_list_key"

    private val sharedPreferencesHelper = SharedPreferencesHelper.instance

    fun getList(): ArrayList<ProspectLogModel>? {
        return sharedPreferencesHelper.restoreList(ProspectLogModel::class, LOG_LIST_KEY) as ArrayList<ProspectLogModel>?
    }

    fun saveList(list: ArrayList<ProspectLogModel>) {
        sharedPreferencesHelper.saveObject(list, LOG_LIST_KEY)
    }

    fun removeList() {
        sharedPreferencesHelper.remove(LOG_LIST_KEY)
    }

    fun addItemToList(prospectLogModel: ProspectLogModel){
        val list = getList()
        list?.let { it.add(prospectLogModel); saveList(it) }
    }

}