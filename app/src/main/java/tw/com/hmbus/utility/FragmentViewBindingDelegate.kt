package tw.com.hmbus.utility

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    private val viewBindingClazz: Class<T>
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        thisRef.viewLifecycleOwnerLiveData.observe(thisRef) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    viewLifecycleOwner.lifecycle.removeObserver(this)
                    binding = null
                }
            })
        }

        return bind(thisRef.requireView()).also { binding = it }
    }

    @Suppress("UNCHECKED_CAST")
    private fun bind(view: View): T {
        val method = viewBindingClazz.getMethod("bind", View::class.java)
        return method.invoke(null, view) as T
    }
}

inline fun <reified T : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate(T::class.java)
