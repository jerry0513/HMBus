package tw.com.hmbus.ui.searchRoute

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.data.remote.BusRoute
import tw.com.hmbus.domain.SearchRouteCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRouteCase: SearchRouteCase
) : ViewModel() {

    val busRouteResult = MutableLiveData<Result<List<BusRoute>>>()

    fun searchBusRoute(routeName: String) = viewModelScope.launch {
        busRouteResult.postValue(Result.Loading)

        val params = SearchRouteCase.Params("Taipei", routeName)
        searchRouteCase.executeAndGet(params)
            .catch { t ->
                busRouteResult.postValue(Result.Error(t))
            }
            .collect {
                busRouteResult.postValue(Result.Success(it))
            }
    }
}
