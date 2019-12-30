package io.github.toranoko0518.demo.retromock.api

import io.github.toranoko0518.demo.retromock.vo.Auth
import io.github.toranoko0518.demo.retromock.vo.Payment
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("api/v1/auth")
    fun getAuth(@Query("id") id: String): Single<Auth>

    @GET("api/v1/payment")
    fun getPayment(@Query("token") token: String): Single<List<Payment>>
}
