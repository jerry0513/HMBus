package tw.com.hmbus.ui.realTime

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tw.com.core.data.model.BusN1EstimateTime
import tw.com.hmbus.model.Result
import tw.com.hmbus.domain.GetEstimatedTimeOfStopCase
import javax.inject.Inject

@HiltViewModel
class RealTimeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getEstimatedTimeOfStopCase: GetEstimatedTimeOfStopCase
) : ViewModel() {

    val estimatedTimeOfArrivalResult = MutableLiveData<Result<Map<Int, List<BusN1EstimateTime>>>>()

    fun getEstimatedTimeOfArrival(city: String, routeName: String) = viewModelScope.launch {
        estimatedTimeOfArrivalResult.postValue(Result.Loading)

        val params = GetEstimatedTimeOfStopCase.Params(city, routeName)
        getEstimatedTimeOfStopCase.executeAndGet(params)
            .catch { t ->
                estimatedTimeOfArrivalResult.postValue(Result.Error(t))
            }
            .collect {
                estimatedTimeOfArrivalResult.postValue(Result.Success(it))
            }
    }
}
