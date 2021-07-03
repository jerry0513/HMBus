package tw.com.hmbus.utility

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun<T> LiveData<T>.observeData(fragment: Fragment, observer: Observer<T>) {
    this.observe(fragment.viewLifecycleOwner, observer)
}