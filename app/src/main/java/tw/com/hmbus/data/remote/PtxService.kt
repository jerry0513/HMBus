package tw.com.hmbus.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PtxService {

    @GET("v2/Bus/EstimatedTimeOfArrival/City/{City}/{RouteName}")
    fun getEstimatedTimeOfArrival(
        @Path("City") city: String,
        @Path("RouteName") routeName: String,
        @Query("\$filter") filter: String?
    ): Call<List<BusN1EstimateTime>>

    @GET("v2/Bus/Route/City/{City}/{RouteName}")
    fun getRoute(
        @Path("City") city: String,
        @Path("RouteName") routeName: String
    ): Call<List<BusRoute>>

    @GET("v2/Bus/StopOfRoute/City/{City}/{RouteName}")
    fun getStopOfRoute(
        @Path("City") city: String,
        @Path("RouteName") routeName: String,
        @Query("\$filter") filter: String?
    ): Call<List<BusStopOfRoute>>
}
