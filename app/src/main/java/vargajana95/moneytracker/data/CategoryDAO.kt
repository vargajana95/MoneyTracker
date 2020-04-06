package vargajana95.moneytracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDAO {
    @Query("""SELECT * FROM `Category`""")
    fun getAllCategories(): List<Category>

    @Query("""SELECT * FROM `Category` WHERE id=:id""")
    fun getCategoryById(id: Long): Category
    }