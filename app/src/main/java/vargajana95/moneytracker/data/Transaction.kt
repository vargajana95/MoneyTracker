package vargajana95.moneytracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var date: String?,
    var amount: Int?,
    var memo: String?,
    var name: String?,
    var categoryId: Long?
)