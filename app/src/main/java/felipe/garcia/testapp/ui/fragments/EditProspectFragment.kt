package felipe.garcia.testapp.ui.fragments


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import felipe.garcia.testapp.R
import felipe.garcia.testapp.base.BaseFragment
import felipe.garcia.testapp.databinding.FragmentEditProspectBinding
import felipe.garcia.testapp.models.ProspectModel
import felipe.garcia.testapp.ui.viewModels.EditProspectViewModel

private const val PROSPECT_OBJECT = "prospect_object"

class EditProspectFragment : BaseFragment() {

    private lateinit var binding: FragmentEditProspectBinding
    private lateinit var prospectModel: ProspectModel
    private lateinit var viewModel: EditProspectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            prospectModel = it.getSerializable(PROSPECT_OBJECT) as ProspectModel
            context?.let { viewModel = EditProspectViewModel(prospectModel, it) }
        }
        subscribe()
    }

    private fun subscribe() {
        subscriptions.addAll(
                viewModel.observableProgressDialog().subscribe(this::progressDialog),
                viewModel.observableShowMessage().subscribe(this::showMessage)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_edit_prospect, container , false)
        binding.viewModel = viewModel
        return binding.root
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
