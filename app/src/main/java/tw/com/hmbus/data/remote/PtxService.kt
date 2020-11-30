package tw.com.hmbus.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PtxService {

    @GET("v2/Bus/EstimatedTimeOfArrival/City/{City}/{RouteName}")
    suspend fun getEstimatedTimeOfArrival(
        @Path("City") city: String,
        @Path("RouteName") routeName: String,
        @Query("\$filter") filter: String?
    ): List<BusN1EstimateTime>

    @GET("v2/Bus/Route/City/{City}/{RouteName}")
    suspend fun getRoute(
        @Path("City") city: String,
        @Path("RouteName") routeName: String
    ): List<BusRoute>

    @GET("v2/Bus/StopOfRoute/City/{City}/{RouteName}")
    suspend fun getStopOfRoute(
        @Path("City") city: String,
        @Path("RouteName") routeName: String,
        @Query("\$filter") filter: String?
    ): List<BusStopOfRoute>
}
