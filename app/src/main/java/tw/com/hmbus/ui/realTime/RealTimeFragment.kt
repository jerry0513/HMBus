package tw.com.hmbus.ui.realTime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tw.com.hmbus.data.Result
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

        with(binding.estimatedTimeList) {
            layoutManager = LinearLayoutManager(context)
            adapter = EstimatedTimeAdapter()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        realTimeViewModel.estimatedTimeOfArrivalResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    (binding.estimatedTimeList.adapter as EstimatedTimeAdapter).data = result.data
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, result.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        realTimeViewModel.getEstimatedTimeOfArrival(args.routeName)

        return binding.root
    }
}