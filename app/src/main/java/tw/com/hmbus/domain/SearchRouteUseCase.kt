package tw.com.hmbus.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import tw.com.hmbus.dagger.IoDispatcher
import tw.com.hmbus.data.PtxRepository
import tw.com.hmbus.data.remote.BusRoute
import javax.inject.Inject

class SearchRouteUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val ptxRepository: PtxRepository
): UseCase() {

    suspend operator fun invoke(city: String, routeName: String): Flow<List<BusRoute>> {
        return ptxRepository.getBusRoute(city, routeName)
            .flowOn(ioDispatcher)
    }
}