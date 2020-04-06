package vargajana95.moneytracker.interactor

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vargajana95.moneytracker.model.TransactionResult
import vargajana95.moneytracker.model.TransactionsResult
import vargajana95.moneytracker.network.YnabApi
import javax.inject.Inject

class TransactionInteractor @Inject constructor(
    private val ynabApi: YnabApi
) {

    fun getTransactions(): Single<List<TransactionResult>> {
        return ynabApi.getTransactions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it?.data?.transactions }
    }
}