package company.home.intergrupoapp.ui.fragments


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import company.home.intergrupoapp.R
import company.home.intergrupoapp.base.BaseFragment
import company.home.intergrupoapp.databinding.FragmentProspectListBinding
import company.home.intergrupoapp.models.ProspectModel
import company.home.intergrupoapp.ui.OPEN_DETAILS
import company.home.intergrupoapp.ui.ProspectDetailEvent
import company.home.intergrupoapp.ui.adapters.MyProspectLogRecyclerViewAdapter
import company.home.intergrupoapp.ui.viewModels.ProspectListViewModel


class ProspectListFragment : BaseFragment() {

    private lateinit var binding: FragmentProspectListBinding
    private lateinit var viewModel: ProspectListViewModel
    private lateinit var adapter: MyProspectLogRecyclerViewAdapter
    private var listItems = ArrayList<ProspectModel>()

    override fun onClick(item: Any) {
        val event = ProspectDetailEvent(item as ProspectModel, OPEN_DETAILS)
        eventBus.post(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let { viewModel = ProspectListViewModel(it) }
        subscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_prospect_list,container , false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeRefreshWidget()
    }

    private fun subscribe() {
        subscriptions.addAll(
                viewModel.onProspectList().subscribe(this::onProspectList),
                viewModel.observableProgressDialog().subscribe(this::progressDialog)
        )
    }

    private fun onProspectList(list: ArrayList<ProspectModel>) {
        adapter.replaceItems(list)
        adapter.notifyDataSetChanged()
        hideSwipeProgressBar()
    }

    private fun initSwipeRefreshWidget() {
        binding.swipeLayout.setOnRefreshListener { direction ->
            if (direction == SwipyRefreshLayoutDirection.TOP) viewModel.onSwipeTop()
        }
        activity?.let {
            binding.swipeLayout.setColorSchemeColors(
                    ContextCompat.getColor(it, R.color.colorPrimary),
                    ContextCompat.getColor(it, R.color.colorAccent)
            )
        }
    }

    private fun hideSwipeProgressBar() {
        binding.swipeLayout.isRefreshing = false
    }

    private fun initRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = mLayoutManager
        adapter = MyProspectLogRecyclerViewAdapter(listItems, this)
        binding.recyclerView.adapter = adapter
        viewModel.initListView()
    }

    companion object {
        const val FRAGMENT_ID = "Prospect List"
    }
}
