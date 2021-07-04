package tw.com.hmbus.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Call

abstract class Repository {
    fun <T> requestApi(action: Call<T>): Flow<T> {
        return flowOf(action)
            .map {
                val response = it.execute()
                if (response.isSuccessful.not()) {
                    throw HMBusException("${response.code()} ${response.message()}")
                }

                response.body()!!
            }
            .flowOn(Dispatchers.IO)
    }
}
