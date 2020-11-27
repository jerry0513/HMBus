package tw.com.hmbus.ui.realTime

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tw.com.hmbus.data.PtxRepository
import tw.com.hmbus.data.Result
import tw.com.hmbus.data.remote.BusN1EstimateTime

class RealTimeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val ptxRepository: PtxRepository
) : ViewModel() {

    val estimatedTimeOfArrivalResult = MutableLiveData<Result<List<BusN1EstimateTime>>>()

    fun getEstimatedTimeOfArrival(routeName: String) = viewModelScope.launch {
        estimatedTimeOfArrivalResult.postValue(Result.Loading)
        ptxRepository.getEstimatedTimeOfArrival("Taipei", routeName)
            .catch { t ->
                estimatedTimeOfArrivalResult.postValue(Result.Error(t))
            }
            .collect {
                estimatedTimeOfArrivalResult.postValue(Result.Success(it))
            }
    }
}
