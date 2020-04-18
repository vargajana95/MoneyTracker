package vargajana95.moneytracker.model

data class Transaction(
    var id: String,
    var date: String,
    var amount: Int,
    var memo: String?,
    var name: String,
    var category: Category
)