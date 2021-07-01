package tw.com.hmbus.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<in P, out R> {
    abstract suspend fun run(params: P): Flow<R>

    suspend fun executeAndGet(params: P): Flow<R> {
        return run(params).flowOn(Dispatchers.IO)
    }
}
