package tw.com.hmbus.ui.realTime

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.com.hmbus.R
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.databinding.FragmentEstimatedTimeBinding
import tw.com.hmbus.ui.BaseFragment
import tw.com.hmbus.utility.dp
import tw.com.hmbus.utility.observeData
import tw.com.hmbus.utility.viewBinding

class EstimatedTimeFragment : BaseFragment(R.layout.fragment_estimated_time) {

    private val binding: FragmentEstimatedTimeBinding by viewBinding()
    private val realTimeViewModel: RealTimeViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        realTimeViewModel.estimatedTimeOfArrivalResult.observeData(this) { result ->
            when (result) {
                is Result.Success -> {
                    estimatedTimeAdapter.data = result.data.getValue(direction)
                }
                else -> {
                }
            }
        }
    }
}
