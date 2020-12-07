package tw.com.hmbus.ui.realTime

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tw.com.hmbus.data.remote.BusN1EstimateTime
import tw.com.hmbus.data.vo.Result
import tw.com.hmbus.domain.GetEstimatedTimeOfStopUseCase

class RealTimeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getEstimatedTimeOfStopUseCase: GetEstimatedTimeOfStopUseCase
) : ViewModel() {

    val estimatedTimeOfArrivalResult = MutableLiveData<Result<Map<Int, List<BusN1EstimateTime>>>>()

    fun getEstimatedTimeOfArrival(city: String, routeName: String) = viewModelScope.launch {
        estimatedTimeOfArrivalResult.postValue(Result.Loading)
        getEstimatedTimeOfStopUseCase(city, routeName)
            .catch { t ->
                estimatedTimeOfArrivalResult.postValue(Result.Error(t))
            }
            .collect {
                estimatedTimeOfArrivalResult.postValue(Result.Success(it))
            }
    }
}
