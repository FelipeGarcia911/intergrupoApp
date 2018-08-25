package felipe.garcia.testapp.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import felipe.garcia.testapp.R
import felipe.garcia.testapp.base.BaseFragment
import felipe.garcia.testapp.databinding.FragmentLogBinding
import felipe.garcia.testapp.models.ProspectLogModel
import felipe.garcia.testapp.ui.adapters.ProspectLogRecyclerViewAdapter
import felipe.garcia.testapp.ui.viewModels.LogFragmentViewModel


class LogFragment : BaseFragment() {

    private lateinit var binding: FragmentLogBinding
    private lateinit var adapter: ProspectLogRecyclerViewAdapter
    private var viewModel: LogFragmentViewModel? = null
    private var listItems = ArrayList<ProspectLogModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {  viewModel = LogFragmentViewModel(it) }
        subscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_log,container , false)
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
                viewModel?.onLogList()?.subscribe(this::onLogList),
                viewModel?.observableProgressDialog()?.subscribe(this::progressDialog)
        )
    }

    private fun onLogList(list: ArrayList<ProspectLogModel>){
        adapter.replaceItems(list)
        adapter.notifyDataSetChanged()
        hideSwipeProgressBar()
    }

    private fun initSwipeRefreshWidget() {
        binding.swipeLayout.setOnRefreshListener { direction ->
            if (direction == SwipyRefreshLayoutDirection.TOP) viewModel?.onSwipeTop()
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
        adapter = ProspectLogRecyclerViewAdapter(listItems, this)
        binding.recyclerView.adapter = adapter
        viewModel?.initListView()
    }

    companion object {
        const val FRAGMENT_ID = "Log Fragment"
    }
}
