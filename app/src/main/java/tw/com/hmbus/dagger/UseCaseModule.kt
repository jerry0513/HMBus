package tw.com.hmbus.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import tw.com.hmbus.data.remote.BusN1EstimateTime
import tw.com.hmbus.data.remote.BusRoute
import tw.com.hmbus.domain.GetEstimatedTimeOfStopCase
import tw.com.hmbus.domain.SearchRouteCase
import tw.com.hmbus.domain.UseCase

@Module
@InstallIn(FragmentComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetEstimatedTimeOfStopUseCase(
        getEstimatedTimeOfStopCase: GetEstimatedTimeOfStopCase
    ): UseCase<GetEstimatedTimeOfStopCase.Params, Map<Int, List<BusN1EstimateTime>>>

    @Binds
    abstract fun bindSearchRouteUseCase(
        searchRouteCase: SearchRouteCase
    ): UseCase<SearchRouteCase.Params, List<BusRoute>>
}