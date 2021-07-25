package tw.com.core.data

import kotlinx.coroutines.flow.Flow
import tw.com.core.data.model.BusN1EstimateTime
import tw.com.core.data.model.BusRoute
import tw.com.core.data.remote.PtxService
import tw.com.core.data.model.BusStopOfRoute
import javax.inject.Inject

class PtxRepository @Inject constructor(
    private val ptxService: PtxService
) : Repository() {

    fun getEstimatedTimeOfArrival(
        city: String,
        routeName: String,
        filter: String?
    ): Flow<List<BusN1EstimateTime>> = requestApi(
        ptxService.getEstimatedTimeOfArrival(city, routeName, filter)
    )

    fun getBusRoute(
        city: String,
        routeName: String
    ): Flow<List<BusRoute>> = requestApi(
        ptxService.getRoute(city, routeName)
    )

    fun getStopOfRoute(
        city: String,
        routeName: String,
        filter: String?
    ): Flow<List<BusStopOfRoute>> = requestApi(
        ptxService.getStopOfRoute(city, routeName, filter)
    )
}
