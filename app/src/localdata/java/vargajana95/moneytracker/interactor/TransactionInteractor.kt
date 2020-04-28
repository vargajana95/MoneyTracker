package vargajana95.moneytracker.interactor

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction
import vargajana95.moneytracker.persistence.dao.CategoryDAO
import vargajana95.moneytracker.persistence.dao.TransactionDAO
import javax.inject.Inject

@Singleton
class TransactionInteractor @Inject constructor(
    private val transactionDao: TransactionDAO,
    private val categoryDao: CategoryDAO
) {

    fun getTransactions(): Single<List<Transaction>> {
        return transactionDao.getAllTransactions().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.map { t ->
                    Transaction(
                        t.id,
                        t.date!!,
                        t.amount!!,
                        t.memo,
                        t.name!!,
                        categoryDao.getCategoryById(t.categoryId!!).map { c ->
                            Category(
                                c.id,
                                c.name
                            )
                        }.blockingGet()
                    )
                }
            }
    }
}