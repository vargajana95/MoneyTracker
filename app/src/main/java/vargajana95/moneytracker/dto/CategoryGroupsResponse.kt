package vargajana95.moneytracker.dto

data class CategoryGroupsResponse(
    var id: String? = null,
    var name: String? = null,
    var categories: List<CategoryResponse>? = null
)
