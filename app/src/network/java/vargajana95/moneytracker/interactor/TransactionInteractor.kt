package vargajana95.moneytracker.interactor

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.persistence.TransactionData
import vargajana95.moneytracker.network.YnabApi
import javax.inject.Inject

class TransactionInteractor @Inject constructor(
    private val ynabApi: YnabApi
) {

    fun getTransactions(): Single<List<Transaction>> {
        return ynabApi.getTransactions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data?.transactions?.map {t ->
                Transaction(
                    t.id!!,
                    t.date!!,
                    t.amount!!,
                    t.memo,
                    t.name!!,
                    Category(t.categoryId!!, t.categoryName!!)
                )
            } }
    }
}