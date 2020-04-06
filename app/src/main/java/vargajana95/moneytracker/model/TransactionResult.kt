package vargajana95.moneytracker.model

import com.google.gson.annotations.SerializedName

data class TransactionResult(
    var id: String? = null,
    var date: String? = null,
    var amount: Int? = null,
    var memo: String? = null,
    @SerializedName("category_name")
    var categoryName: String? = null,
    @SerializedName("payee_name")
    var name: String? = null
)