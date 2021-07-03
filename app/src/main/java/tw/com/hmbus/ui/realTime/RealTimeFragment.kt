package tw.com.hmbus.ui.realTime

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
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
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import tw.com.hmbus.R
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.databinding.FragmentRealTimeBinding
import tw.com.hmbus.ui.BaseFragment
import tw.com.hmbus.utility.observeData
import tw.com.hmbus.utility.viewBinding

@AndroidEntryPoint
class RealTimeFragment : BaseFragment(R.layout.fragment_real_time) {

    private val args: RealTimeFragmentArgs by navArgs()
    private val binding: FragmentRealTimeBinding by viewBinding()
    private val realTimeViewModel: RealTimeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.routeName.text = args.routeName

        fragmentScope.launch {
            var currentTime = 0
            for (event in ticker(3000, 0)) {
                val progress = currentTime % 30 / 30f * 100
                if (progress == 0f)
                    realTimeViewModel.getEstimatedTimeOfArrival("Taipei", args.routeName)

                ObjectAnimator.ofInt(binding.progressBar, "progress", progress.toInt())
                    .setDuration(300)
                    .start()

                currentTime += 3
            }
        }

        realTimeViewModel.estimatedTimeOfArrivalResult.observeData(this) { result ->
            when (result) {
                is Result.Success -> {
                    binding.estimatedTimeViewPager.adapter = EstimatedTimePagerAdapter(
                        result.data.keys.toList(),
                        childFragmentManager,
                        viewLifecycle
                    )
                    TabLayoutMediator(
                        binding.directionTab,
                        binding.estimatedTimeViewPager
                    ) { tab, position ->
                        val directionStops = result.data.values.toList()
                        tab.text = "往${directionStops[position].last().StopName.Zh_tw}"
                    }.attach()
                }
                is Result.Error -> {
                    Toast.makeText(context, result.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
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
