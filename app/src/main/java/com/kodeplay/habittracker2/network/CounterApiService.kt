package com.example.android.marsphotos.network
import com.kodeplay.habittracker2.network.CounterApiData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

private  val BASE_URL =
    "http://127.0.0.1:8080"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface CounterApiService {
    @GET("counterdata")
    suspend fun getCount(): CounterApiData

    @PUT("updatecount")
    suspend fun updateCount (@Query("id") id:Int,@Query("inputcount") inputcount:Int)

    @POST("createcount")
    suspend fun createCount ()
}
object CounterApi{
    val retrofitService : CounterApiService by lazy {
        retrofit.create(CounterApiService::class.java)
    }
}
