package com.example.android.marsphotos.network
import com.kodeplay.habittracker2.network.CounterData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
private  val BASE_URL =
    "http://0.0.0.0:8080"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface CounterApiService {
    @GET("counterdata")
    suspend fun getPhotos(): CounterData
}
object CounterApi{
    val retrofitService : CounterApiService by lazy {
        retrofit.create(CounterApiService::class.java)
    }
}
