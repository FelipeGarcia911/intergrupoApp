package company.home.intergrupoapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.home.intergrupoapp.R
import company.home.intergrupoapp.base.BaseFragment

class EditProspectFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_prospect, container, false)
    }

    companion object {
        const val FRAGMENT_ID = "Edit Prospect"
    }

}
