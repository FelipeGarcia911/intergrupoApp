package company.home.intergrupoapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.home.intergrupoapp.R
import company.home.intergrupoapp.base.BaseFragment
import company.home.intergrupoapp.models.ProspectModel

private const val PROSPECT_OBJECT = "prospect_object"

class EditProspectFragment : BaseFragment() {

    private lateinit var prospectModel: ProspectModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            prospectModel = it.getSerializable(PROSPECT_OBJECT) as ProspectModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_prospect, container, false)
    }

    companion object {
        const val FRAGMENT_ID = "Edit Prospect"
        @JvmStatic
        fun newInstance(prospectModel: ProspectModel) =
                EditProspectFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(PROSPECT_OBJECT, prospectModel)
                    }
                }
    }

}
