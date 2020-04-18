package vargajana95.moneytracker.mock

import io.reactivex.Single
import vargajana95.moneytracker.persistence.CategoryData
import vargajana95.moneytracker.persistence.dao.CategoryDAO

class MockCategoryDAO : CategoryDAO {
    override fun getAllCategories(): Single<List<CategoryData>> {
        return Single.just(listOf(
            CategoryData("c1", "Category 1"),
            CategoryData("c2", "Category 2"),
            CategoryData("c3", "Category 3")
        ))
    }

    override fun getCategoryById(id: String): Single<CategoryData> {
        return Single.just(CategoryData("c1", "Category 1"))
    }


}