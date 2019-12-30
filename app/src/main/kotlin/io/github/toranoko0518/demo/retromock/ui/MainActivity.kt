package io.github.toranoko0518.demo.retromock.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.github.toranoko0518.demo.retromock.R
import io.github.toranoko0518.demo.retromock.api.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Api.create()

        val disposable = api.getAuth("xxx")
            .flatMap { api.getPayment(it.token) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { Log.d("DEBUG", "Success: $it") },
                onError = { Log.d("DEBUG", "Error: $it") }
            )

        disposables.add(disposable)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}
