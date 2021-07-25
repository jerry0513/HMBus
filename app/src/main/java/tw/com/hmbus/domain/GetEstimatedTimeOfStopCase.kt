package tw.com.hmbus.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import tw.com.core.data.PtxRepository
import tw.com.core.data.model.BusN1EstimateTime
import javax.inject.Inject

class GetEstimatedTimeOfStopCase @Inject constructor(
    private val ptxRepository: PtxRepository
) : UseCase<GetEstimatedTimeOfStopCase.Params, @JvmSuppressWildcards Map<Int, List<BusN1EstimateTime>>>() {

    override fun run(params: Params): Flow<Map<Int, List<BusN1EstimateTime>>> {
        val filter = "RouteName/Zh_tw eq '${params.routeName}'"

        // 路線的站牌內容
        val stopOfRoute = ptxRepository.getStopOfRoute(params.city, params.routeName, filter)

        // 站牌到站時間
        val estimatedTimeOfArrival =
            ptxRepository.getEstimatedTimeOfArrival(params.city, params.routeName, filter)
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
        }
    }

    data class Params(
        val city: String,
        val routeName: String
    )
}
