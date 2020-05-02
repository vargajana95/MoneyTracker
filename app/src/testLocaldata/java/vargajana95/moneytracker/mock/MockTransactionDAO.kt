package vargajana95.moneytracker.mock

import io.reactivex.Single
import vargajana95.moneytracker.persistence.CategoryData
import vargajana95.moneytracker.persistence.TransactionData
import vargajana95.moneytracker.persistence.TransactionWithCategory
import vargajana95.moneytracker.persistence.dao.TransactionDAO

class MockTransactionDAO : TransactionDAO {
    override fun getAllTransactions(): Single<List<TransactionWithCategory>> {
        val transactions = listOf(
            TransactionWithCategory(
                CategoryData("c1", "Cat1"),
                TransactionData("00000", "2020-04-01", 1000, "Short memo", "Test", "c1")
            ),
            TransactionWithCategory(

                CategoryData("c2", "Cat2"),
                TransactionData("111111", "2020-03-01", 2000, "Short memo2", "Test2", "c2")
            ),
            TransactionWithCategory(

                CategoryData("c3", "Cat3"),
                TransactionData("222222", "2020-02-01", 3000, "Short memo3", "Test3", "c3")
            )
        )

        return Single.just(transactions)
    }

    override fun insertTransactions(vararg transaction: TransactionData) {
    }

}