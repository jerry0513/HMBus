package tw.com.hmbus.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tw.com.hmbus.dagger.IoDispatcher
import tw.com.hmbus.data.remote.PtxService
import javax.inject.Inject

class PtxRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val ptxService: PtxService
) : Repository() {

    suspend fun getEstimatedTimeOfArrival(city: String, routeName: String, filter: String?) = flow {
        emit(ptxService.getEstimatedTimeOfArrival(city, routeName, filter))
    }.flowOn(ioDispatcher)

    suspend fun getBusRoute(city: String, routeName: String) = flow {
        emit(ptxService.getRoute(city, routeName))
    }.flowOn(ioDispatcher)

    suspend fun getStopOfRoute(city: String, routeName: String, filter: String?) = flow {
        emit(ptxService.getStopOfRoute(city, routeName, filter))
    }.flowOn(ioDispatcher)
}
