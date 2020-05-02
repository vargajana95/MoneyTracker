package vargajana95.moneytracker.network

import io.reactivex.Single
import retrofit2.http.*
import vargajana95.moneytracker.dto.*

interface YnabApi {

    @GET("/v1/budgets/330acff1-644b-400a-87b4-7237741a43e2/transactions")
    fun getTransactions(): Single<TransactionsResult>

    @POST("/v1/budgets/330acff1-644b-400a-87b4-7237741a43e2/transactions")
    fun createTransaction(@Body createTransactionRequest: CreateTransactionRequestData): Single<CreateTransactionResponse>

    @GET("/v1/budgets/330acff1-644b-400a-87b4-7237741a43e2/categories")
    fun getCategories(): Single<CategoriesResponse>
}