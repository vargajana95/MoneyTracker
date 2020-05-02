package vargajana95.moneytracker.mock

import io.reactivex.Single
import vargajana95.moneytracker.dto.*
import vargajana95.moneytracker.network.YnabApi

class MockYnabApi : YnabApi {
    override fun getTransactions(): Single<TransactionsResult> {
        val transactionsResult = TransactionsResult(TransactionsResultData(listOf(
            TransactionResult("00000","2020-04-01",1000,"Short memo", "Test","Test Transaction 1", "Test1"),
            TransactionResult("111111","2020-03-01",2000,"Short memo2", "Test2","Test Transaction 2", "Test2"),
            TransactionResult("222222","2020-02-01",3000,"Short memo3", "Test3","Test Transaction 3", "Test3")
        )))

        return Single.just(transactionsResult)
    }

    override fun createTransaction(createTransactionRequest: CreateTransactionRequestData): Single<CreateTransactionResponse> {
        val createTransactionResponse = CreateTransactionResponse(CreateTransactionData(
            TransactionResult("0000000", createTransactionRequest.transaction.date,
                createTransactionRequest.transaction.amount,
                createTransactionRequest.transaction.memo, "111111",
                "Cat1",
                createTransactionRequest.transaction.name)))

        return Single.just(createTransactionResponse)
    }

    override fun getCategories(): Single<CategoriesResponse> {
        val categoriesResponse = CategoriesResponse(CategoriesResponseData(listOf(CategoryGroupsResponse("000000","CatGroup1",
            listOf(CategoryResponse("1111","Cat1"))))
        ))

        return Single.just(categoriesResponse)
    }


}