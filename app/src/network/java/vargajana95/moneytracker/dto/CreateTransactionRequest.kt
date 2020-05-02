package vargajana95.moneytracker.dto

import com.google.gson.annotations.SerializedName

data class CreateTransactionRequest(
    @SerializedName("account_id")
    var accountId: String? = null,
    var date: String? = null,
    var amount: Int? = null,
    @SerializedName("payee_name")
    var name: String? = null,
    @SerializedName("category_id")
    var categoryId: String? = null,
    var memo: String? = null
)