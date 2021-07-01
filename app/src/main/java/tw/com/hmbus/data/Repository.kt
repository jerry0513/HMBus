package tw.com.hmbus.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

abstract class Repository {
    suspend fun <T> requestApi(action: suspend () -> T): Flow<T> {
        return flowOf(action.invoke()).flowOn(Dispatchers.IO)
    }
}
