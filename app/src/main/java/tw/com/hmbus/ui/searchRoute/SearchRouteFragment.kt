package tw.com.hmbus.ui.searchRoute

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tw.com.hmbus.R
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.databinding.FragmentSearchRouteBinding
import tw.com.hmbus.ui.home.BusRouteAdapter
import tw.com.hmbus.utility.viewBinding
import tw.com.hmbus.widget.DividerItemDecoration

@AndroidEntryPoint
class SearchRouteFragment : Fragment(R.layout.fragment_search_route) {

    private val binding: FragmentSearchRouteBinding by viewBinding()
    private val searchViewModel: SearchViewModel by viewModels()

    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchEt.addTextChangedListener {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(1000)
                searchViewModel.searchBusRoute(binding.searchEt.text.toString())
            }
        }

        with(binding.busRouteList) {
            layoutManager = LinearLayoutManager(context)
            adapter = BusRouteAdapter().apply {
                onItemClickListener = { busRoute ->
                    val action = SearchRouteFragmentDirections.toRealTimeFragment(busRoute.RouteName.Zh_tw)
                    findNavController().navigate(action)
                }
            }
            addItemDecoration(DividerItemDecoration())
        }

        searchViewModel.busRouteResult.observe(viewLifecycleOwner, { result ->
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
        })
    }
}