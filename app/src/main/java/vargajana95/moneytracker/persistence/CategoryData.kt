package vargajana95.moneytracker.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryData(
    @PrimaryKey
    var id: String,
    var name: String
)