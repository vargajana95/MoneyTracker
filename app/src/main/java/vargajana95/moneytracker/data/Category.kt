package vargajana95.moneytracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: String? = null,
    var name: String? = null
)