package tw.com.hmbus.data

import kotlinx.coroutines.flow.Flow
import tw.com.hmbus.data.remote.BusN1EstimateTime
import tw.com.hmbus.data.remote.BusRoute
import tw.com.hmbus.data.remote.PtxService
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
    ) = requestApi(
        ptxService.getStopOfRoute(city, routeName, filter)
    )
}
