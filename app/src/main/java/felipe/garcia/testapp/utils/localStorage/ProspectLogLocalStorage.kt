package felipe.garcia.testapp.utils.localStorage

import felipe.garcia.testapp.models.ProspectLogModel


class ProspectLogLocalStorage {

    private val LOG_LIST_KEY = "log_list_key"

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
        var list = getList()
        list?.add(prospectLogModel) ?:let { list = ArrayList<ProspectLogModel>(); list?.add(prospectLogModel) }
        list?.let { saveList(it) }
    }

}