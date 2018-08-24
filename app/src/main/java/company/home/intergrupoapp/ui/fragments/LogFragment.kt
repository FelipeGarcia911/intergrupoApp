package company.home.intergrupoapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.home.intergrupoapp.R
import company.home.intergrupoapp.base.BaseFragment
import company.home.intergrupoapp.ui.adapters.MyProspectLogRecyclerViewAdapter
import company.home.intergrupoapp.ui.viewModels.LogFragmentViewModel


class LogFragment : BaseFragment() {

    private lateinit var listAdapter: MyProspectLogRecyclerViewAdapter
    private lateinit var viewModel: LogFragmentViewModel

    override fun onClick(item: Any) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = LogFragmentViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    companion object {
        const val FRAGMENT_ID = "Log Fragment"
    }
}
