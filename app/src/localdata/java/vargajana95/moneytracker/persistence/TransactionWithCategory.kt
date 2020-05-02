package vargajana95.moneytracker.persistence

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithCategory (
    @Embedded
    val category: CategoryData,

    @Relation(parentColumn = "id", entityColumn = "categoryId")
    val transaction: TransactionData

)