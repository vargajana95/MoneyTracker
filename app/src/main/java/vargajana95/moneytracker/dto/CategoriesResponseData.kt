package vargajana95.moneytracker.dto

import com.google.gson.annotations.SerializedName

data class CategoriesResponseData(
    @SerializedName("category_groups")
    var categoryGroups: List<CategoryGroupsResponse>
)
