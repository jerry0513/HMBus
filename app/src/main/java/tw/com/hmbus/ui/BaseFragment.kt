package tw.com.hmbus.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    val fragmentScope = viewLifecycleOwner.lifecycleScope
    val viewLifecycle = viewLifecycleOwner.lifecycle
}