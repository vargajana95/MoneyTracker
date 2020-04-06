package vargajana95.moneytracker.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import vargajana95.moneytracker.model.CategoriesResponse
import vargajana95.moneytracker.model.CreateTransactionRequest
import vargajana95.moneytracker.model.TransactionsResult

interface YnabApi {

    @GET("/budgets/db4cb2a2-1d97-44bb-8762-9821bd0fd8b9/transactions")
    fun getTransactions(@Header("Authorization") authorization: String): Call<TransactionsResult>

    @POST("/budgets/db4cb2a2-1d97-44bb-8762-9821bd0fd8b9/transactions")
    fun createTransaction(@Header("Authorization") authorization: String, @Body createTransactionRequest: CreateTransactionRequest)

    @GET("/budgets/db4cb2a2-1d97-44bb-8762-9821bd0fd8b9/categories")
    fun getCategories(@Header("Authorization") authorization: String): Call<CategoriesResponse>
}