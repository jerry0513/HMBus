package tw.com.hmbus.ui.searchRoute

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tw.com.hmbus.R
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.databinding.FragmentSearchRouteBinding
import tw.com.hmbus.ui.BaseFragment
import tw.com.hmbus.ui.home.BusRouteAdapter
import tw.com.hmbus.utility.observeData
import tw.com.hmbus.utility.viewBinding
import tw.com.hmbus.widget.DividerItemDecoration

@AndroidEntryPoint
class SearchRouteFragment : BaseFragment(R.layout.fragment_search_route) {

    private val binding: FragmentSearchRouteBinding by viewBinding()
    private val searchRouteViewModel: SearchRouteViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchEt.addTextChangedListener {
            searchRouteViewModel.searchBusRoute(it.toString())
        }

        with(binding.busRouteList) {
            layoutManager = LinearLayoutManager(context)
            adapter = BusRouteAdapter().apply {
                onItemClickListener = { busRoute ->
                    val action =
                        SearchRouteFragmentDirections.toRealTimeFragment(busRoute.RouteName.Zh_tw)
                    findNavController().navigate(action)
                }
            }
            addItemDecoration(DividerItemDecoration())
        }

        searchRouteViewModel.busRouteResult.observeData(this) { result ->
            when (result) {
                is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    (binding.busRouteList.adapter as BusRouteAdapter).data = result.data
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}