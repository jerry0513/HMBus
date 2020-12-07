package tw.com.hmbus.ui.realTime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.databinding.FragmentRealTimeBinding

@AndroidEntryPoint
class RealTimeFragment : Fragment() {

    private val args: RealTimeFragmentArgs by navArgs()
    private val realTimeViewModel: RealTimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRealTimeBinding.inflate(inflater, container, false)

        binding.routeName.text = args.routeName

        realTimeViewModel.estimatedTimeOfArrivalResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.estimatedTimeViewPager.adapter = EstimatedTimePagerAdapter(
                        result.data.keys.toList(),
                        childFragmentManager,
                        viewLifecycleOwner.lifecycle
                    )
                    TabLayoutMediator(binding.directionTab, binding.estimatedTimeViewPager) { tab, position ->
                        tab.text = result.data.values.toList()[position].last().StopName.Zh_tw
                    }.attach()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        realTimeViewModel.getEstimatedTimeOfArrival("Taipei", args.routeName)

        return binding.root
    }
}

class EstimatedTimePagerAdapter(
    private val directions: List<Int>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = directions.size

    override fun createFragment(position: Int): Fragment = EstimatedTimeFragment().apply {
        arguments = bundleOf("direction" to directions[position])
    }
}
