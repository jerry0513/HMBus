package tw.com.hmbus.ui.realTime

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.databinding.FragmentEstimatedTimeBinding
import tw.com.hmbus.utility.dp

class EstimatedTimeFragment : Fragment() {

    private val realTimeViewModel: RealTimeViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEstimatedTimeBinding.inflate(layoutInflater, container, false)
        val direction = requireArguments().getInt("direction")

        val estimatedTimeAdapter = EstimatedTimeAdapter()
        with(binding.estimatedTimeList) {
            layoutManager = LinearLayoutManager(context)
            adapter = estimatedTimeAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                private val offset = 16.dp

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    if (parent.getChildAdapterPosition(view) == 0)
                        outRect.top = offset
                    outRect.left = offset
                    outRect.right = offset
                    outRect.bottom = offset
                }
            })
        }

        realTimeViewModel.estimatedTimeOfArrivalResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Success -> {
                    estimatedTimeAdapter.data = result.data.getValue(direction)
                }
                else -> {
                }
            }
        })

        return binding.root
    }
}
