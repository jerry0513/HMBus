package tw.com.hmbus.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.hmbus.data.BaseRepository
import tw.com.hmbus.data.PtxRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPtxRepository(ptxRepository: PtxRepository): BaseRepository
}
