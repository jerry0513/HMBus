package tw.com.hmbus.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import tw.com.hmbus.dagger.IoDispatcher
import tw.com.hmbus.data.PtxRepository
import tw.com.hmbus.data.remote.BusN1EstimateTime
import javax.inject.Inject

class GetEstimatedTimeOfStopUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val ptxRepository: PtxRepository
) : UseCase() {

    suspend operator fun invoke(
        city: String,
        routeName: String
    ): Flow<Map<Int, List<BusN1EstimateTime>>> {
        val filter = "RouteName/Zh_tw eq '$routeName'"

        // 路線的站牌內容
        val stopOfRoute = ptxRepository.getStopOfRoute(city, routeName, filter)

        // 站牌到站時間
        val estimatedTimeOfArrival =
            ptxRepository.getEstimatedTimeOfArrival(city, routeName, filter)
        return stopOfRoute.combine(estimatedTimeOfArrival) { routes, arrivals ->
            val routeMap = hashMapOf<Int, List<BusN1EstimateTime>>()
            for (route in routes) {
                routeMap[route.Direction] = route.Stops.map { stop ->
                    val arrival = arrivals.first { it.StopUID == stop.StopUID }
                    BusN1EstimateTime(
                        StopUID = stop.StopUID,
                        StopName = stop.StopName,
                        EstimateTime = arrival.EstimateTime,
                        StopStatus = arrival.StopStatus
                    )
                }
            }
            routeMap
        }.flowOn(ioDispatcher)
    }
}
