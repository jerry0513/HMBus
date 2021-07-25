package tw.com.hmbus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import tw.com.core.data.model.BusN1EstimateTime
import tw.com.core.data.model.BusRoute
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