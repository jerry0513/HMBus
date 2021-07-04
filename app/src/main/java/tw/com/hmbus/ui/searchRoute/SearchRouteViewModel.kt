package tw.com.hmbus.ui.searchRoute

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tw.com.hmbus.data.remote.BusRoute
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.domain.SearchRouteCase
import javax.inject.Inject

@HiltViewModel
class SearchRouteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRouteCase: SearchRouteCase
) : ViewModel() {

    val busRouteResult = MutableLiveData<Result<List<BusRoute>>>()

    private val searchFlow = MutableSharedFlow<String>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    init {
        viewModelScope.launch {
            searchFlow
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .onEach { busRouteResult.value = Result.Loading }
                .flatMapLatest {
                    val params = SearchRouteCase.Params("Taipei", it)
                    searchRouteCase.executeAndGet(params)
                }
                .catch { t ->
                    busRouteResult.value = Result.Error(t)
                }
                .collect {
                    busRouteResult.value = Result.Success(it)
                }
        }
    }


    fun searchBusRoute(routeName: String) {
        searchFlow.tryEmit(routeName)
    }
}
