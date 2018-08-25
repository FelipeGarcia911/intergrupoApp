package felipe.garcia.testapp.ui.viewModels

import android.content.Context
import android.databinding.ObservableField
import felipe.garcia.testapp.base.BaseViewModel
import felipe.garcia.testapp.models.ProspectLogModel
import felipe.garcia.testapp.models.ProspectModel
import felipe.garcia.testapp.utils.localStorage.ProspectListLocalStorage
import felipe.garcia.testapp.utils.localStorage.ProspectLogLocalStorage
import java.util.*

class EditProspectViewModel(private val prospectModel: ProspectModel, context: Context): BaseViewModel(context) {

    var name = ObservableField<String>()
    var lastName = ObservableField<String>()
    var telephone = ObservableField<String>()

    var nameError = ObservableField<String>()
    var lastNameError = ObservableField<String>()
    var telephoneError = ObservableField<String>()

    init {
        name.set(prospectModel.name)
        lastName.set(prospectModel.lastName)
        telephone.set(prospectModel.telephone)
    }

    fun onSubmit(){
        val nameString = name.get()?:""
        val lastNameString = lastName.get()?:""
        val telephoneString = telephone.get()?:""
        when{
            nameString.isEmpty() ->nameError.set("Nombre invalido")
            lastNameString.isEmpty() -> lastNameError.set("Apellido invalido")
            telephoneString.isEmpty() -> telephoneError.set("Telefono invalido")
            else ->{
                clearErrors()
                updateProspect(nameString, lastNameString, telephoneString)
                val isUpdated = updateProspectList()
                if (isUpdated){
                    createLog(prospectModel.identification, nameString, lastNameString, telephoneString)
                    showMessage("Prospecto actualizado correctamente")
                }else{
                    showMessage("Prospecto NO actualizado")
                }
            }
        }
    }

    private fun updateProspect(name: String, lastName:String, telephone: String){
        prospectModel.name = name
        prospectModel.lastName = lastName
        prospectModel.telephone = telephone
    }

    private fun updateProspectList(): Boolean {
        val prospectListLocalStorage = ProspectListLocalStorage()
        val list = prospectListLocalStorage.getList()
        return list?.let { it ->
            val index = it.indexOfFirst { it.identification == prospectModel.identification }
            it[index] = prospectModel
            prospectListLocalStorage.saveList(it)
            true
        }?:false
    }

    private fun createLog(identification: String, name: String, lastName:String, telephone: String){
        val prospectLogModel = ProspectLogModel(identification, name, lastName, telephone,Calendar.getInstance().time.toString())
        ProspectLogLocalStorage().addItemToList(prospectLogModel)
    }

    private fun clearErrors(){
        nameError.set("")
        lastNameError.set("")
        telephoneError.set("")
    }


}