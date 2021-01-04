package tw.com.hmbus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tw.com.hmbus.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.searchFab.setOnClickListener {
            val action = HomeFragmentDirections.toSearchRouteFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}
