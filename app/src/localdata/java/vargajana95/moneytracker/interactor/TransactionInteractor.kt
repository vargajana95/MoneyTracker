package vargajana95.moneytracker.interactor

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.persistence.TransactionData
import vargajana95.moneytracker.persistence.dao.CategoryDAO
import vargajana95.moneytracker.persistence.dao.TransactionDAO
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionInteractor @Inject constructor(
    private val transactionDao: TransactionDAO,
    private val categoryDao: CategoryDAO
) {

    val fetchedTransactions: BehaviorSubject<List<Transaction>> =
        BehaviorSubject.create<List<Transaction>>()


    @SuppressLint("CheckResult")
    fun fetchTransactions() {
        transactionDao.getAllTransactions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {


                it.map { t ->
                    Transaction(
                        t.transaction.id,
                        t.transaction.date!!,
                        t.transaction.amount!! / 1000,
                        t.transaction.memo,
                        t.transaction.name!!,
                        Category(t.category.id, t.category.name)
                    )
                }
            }.subscribe { t ->
                fetchedTransactions.onNext(t!!)
            }
    }

    fun createNewTransaction(transaction: Transaction): Single<Transaction> {
        val id = UUID.randomUUID().toString()
        transactionDao.insertTransactions(
            TransactionData(
                id,
                transaction.date,
                transaction.amount * 1000,
                transaction.memo,
                transaction.name,
                transaction.category.id
            )
        );
        return Single.just(
            Transaction(
                id,
                transaction.date,
                transaction.amount,
                transaction.memo,
                transaction.name,
                Category(transaction.category.id, "")
            )
        )
    }

    fun getCategories(): Single<List<Category>> {
        return categoryDao.getAllCategories().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.map { c ->
                    Category(c.id, c.name)
                }.toList()
            }
    }

}