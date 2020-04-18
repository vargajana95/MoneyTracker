package vargajana95.moneytracker.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionData(
    @PrimaryKey
    var id: String,
    var date: String?,
    var amount: Int?,
    var memo: String?,
    var name: String?,
    var categoryId: String?
)