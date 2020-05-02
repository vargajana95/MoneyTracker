package vargajana95.moneytracker.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import vargajana95.moneytracker.model.Category

@Entity(foreignKeys = [ForeignKey(entity = Category::class,
    parentColumns = ["id"],
    childColumns = ["categoryId"])])
data class TransactionData(
    @PrimaryKey
    var id: String,
    var date: String?,
    var amount: Int?,
    var memo: String?,
    var name: String?,
    var categoryId: String?
)