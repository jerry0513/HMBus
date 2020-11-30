package tw.com.hmbus.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import tw.com.hmbus.domain.GetEstimatedTimeOfStopUseCase
import tw.com.hmbus.domain.UseCase

@Module
@InstallIn(FragmentComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetEstimatedTimeOfStopUseCase(getEstimatedTimeOfStopUseCase: GetEstimatedTimeOfStopUseCase): UseCase
}