package tw.com.hmbus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tw.com.hmbus.R
import tw.com.hmbus.databinding.FragmentHomeBinding
import tw.com.hmbus.utility.viewBinding

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFab.setOnClickListener {
            val action = HomeFragmentDirections.toSearchRouteFragment()
            findNavController().navigate(action)
        }
    }
}
