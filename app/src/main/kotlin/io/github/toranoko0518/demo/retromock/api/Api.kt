package io.github.toranoko0518.demo.retromock.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

interface Api {

    companion object {

        fun create(): Service {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://mock.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val behavior = NetworkBehavior.create()
            behavior.setDelay(100, TimeUnit.MILLISECONDS)
            behavior.setVariancePercent(0)
            behavior.setFailurePercent(0)
            behavior.setErrorPercent(0)

            val mockRetrofit = MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build()

            val delegate = mockRetrofit.create(Service::class.java)

            return MockService(delegate)
        }
    }
}
