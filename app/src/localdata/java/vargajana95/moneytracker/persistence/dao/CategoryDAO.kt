package vargajana95.moneytracker.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import vargajana95.moneytracker.persistence.CategoryData

@Dao
interface CategoryDAO {
    @Query("""SELECT * FROM CategoryData""")
    fun getAllCategories(): Single<List<CategoryData>>

    @Query("""SELECT * FROM CategoryData WHERE id=:id""")
    fun getCategoryById(id: String): Single<CategoryData>
    }