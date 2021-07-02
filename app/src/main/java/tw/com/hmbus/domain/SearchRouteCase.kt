package tw.com.hmbus.domain

import kotlinx.coroutines.flow.Flow
import tw.com.hmbus.data.PtxRepository
import tw.com.hmbus.data.remote.BusRoute
import javax.inject.Inject

class SearchRouteCase @Inject constructor(
    private val ptxRepository: PtxRepository
): UseCase<SearchRouteCase.Params, @JvmSuppressWildcards List<BusRoute>>() {

    override fun run(params: Params): Flow<List<BusRoute>> {
        return ptxRepository.getBusRoute(params.city, params.routeName)
    }

    data class Params(
        val city: String,
        val routeName: String
    )
}