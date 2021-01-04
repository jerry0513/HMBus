package tw.com.hmbus.ui.searchRoute

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.data.remote.BusRoute
import tw.com.hmbus.domain.SearchRouteUseCase

class SearchViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val searchRouteUseCase: SearchRouteUseCase
) : ViewModel() {

    val busRouteResult = MutableLiveData<Result<List<BusRoute>>>()

    fun searchBusRoute(routeName: String) = viewModelScope.launch {
        busRouteResult.postValue(Result.Loading)
        searchRouteUseCase("Taipei", routeName)
            .catch { t ->
                busRouteResult.postValue(Result.Error(t))
            }
            .collect {
                busRouteResult.postValue(Result.Success(it))
            }
    }
}