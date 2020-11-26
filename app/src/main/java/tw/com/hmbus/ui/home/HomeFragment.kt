package tw.com.hmbus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tw.com.hmbus.data.Result
import tw.com.hmbus.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.searchBtn.setOnClickListener {
            homeViewModel.searchBusRoute(binding.searchEt.text.toString())
        }

        with(binding.busRouteList) {
            layoutManager = LinearLayoutManager(context)
            adapter = BusRouteAdapter()
        }

        homeViewModel.busRouteResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    (binding.busRouteList.adapter as BusRouteAdapter).data = result.data
                }
                is Result.Error -> binding.progressBar.visibility = View.GONE
            }
        })

        return binding.root
    }
}
