package vargajana95.moneytracker.network

import io.reactivex.Single
import retrofit2.http.*
import vargajana95.moneytracker.dto.CategoriesResponse
import vargajana95.moneytracker.dto.CreateTransactionRequest
import vargajana95.moneytracker.dto.CreateTransactionResponse
import vargajana95.moneytracker.dto.TransactionsResult

interface YnabApi {

    @GET("/v1/budgets/db4cb2a2-1d97-44bb-8762-9821bd0fd8b9/transactions")
    fun getTransactions(): Single<TransactionsResult>

    @POST("/v1/budgets/db4cb2a2-1d97-44bb-8762-9821bd0fd8b9/transactions")
    fun createTransaction(@Body createTransactionRequest: CreateTransactionRequest): Single<CreateTransactionResponse>

    @GET("/v1/budgets/db4cb2a2-1d97-44bb-8762-9821bd0fd8b9/categories")
    fun getCategories(authorization: String): Single<CategoriesResponse>
}