package io.github.toranoko0518.demo.retromock.api

import io.github.toranoko0518.demo.retromock.vo.Auth
import io.github.toranoko0518.demo.retromock.vo.Payment
import io.reactivex.Single
import retrofit2.mock.BehaviorDelegate

class MockService(private val delegate: BehaviorDelegate<Service>) : Service {

    override fun getAuth(id: String): Single<Auth> {
        val auth = Auth("xxx")
        return delegate.returningResponse(auth).getAuth(id)
    }

    override fun getPayment(token: String): Single<List<Payment>> {
        val payment = Payment(1000)
        return delegate.returningResponse(listOf(payment)).getPayment(token)
    }
}
