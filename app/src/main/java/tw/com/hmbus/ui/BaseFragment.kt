package tw.com.hmbus.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    val fragmentScope: LifecycleCoroutineScope by lazy { viewLifecycleOwner.lifecycleScope }
    val viewLifecycle: Lifecycle by lazy { viewLifecycleOwner.lifecycle }
}