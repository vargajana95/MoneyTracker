package vargajana95.moneytracker.interactor

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import vargajana95.moneytracker.dto.CreateTransactionRequest
import vargajana95.moneytracker.dto.CreateTransactionRequestData
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.network.YnabApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionInteractor @Inject constructor(
    private val ynabApi: YnabApi
) {

    val fetchedTransactions: BehaviorSubject<List<Transaction>> = BehaviorSubject.create<List<Transaction>>()


    @SuppressLint("CheckResult")
    fun fetchTransactions() {
        ynabApi.getTransactions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.data?.transactions?.map { t ->
                    Transaction(
                        t.id!!,
                        t.date!!,
                        t.amount!!/1000,
                        t.memo,
                        t.name!!,
                        Category(t.categoryId!!, t.categoryName!!)
                    )
                }
            }.subscribe {
                t-> fetchedTransactions.onNext(t!!)
            }
    }

    fun createNewTransaction(transaction: Transaction): Single<Transaction> {
        return ynabApi.createTransaction(
            CreateTransactionRequestData(
                CreateTransactionRequest(
                    "e2b19230-afce-4999-b01b-084831d47fe6",
                    transaction.date,
                    transaction.amount * 1000,
                    transaction.name,
                    transaction.category.id,
                    transaction.memo
                )
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val t = it.data.transaction
                Transaction(
                    t.id!!,
                    t.date!!,
                    t.amount!!,
                    t.memo,
                    t.name!!,
                    Category(t.categoryId!!, t.categoryName!!)
                )
            }
    }

    fun getCategories(): Single<List<Category>> {
        return ynabApi.getCategories().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.data.categoryGroups.flatMap { cg ->
                    cg.categories?.map { c ->
                        Category(c.id!!, c.name!!)
                    }!!.toList()
                }
            }
    }
}