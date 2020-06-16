package com.example.nescafe_pushcart.model.listofvendors


import com.google.gson.annotations.SerializedName

data class Pageable(
    var offset: Int?,
    var pageNumber: Int?,
    var pageSize: Int?,
    var paged: Boolean?,
    var sort: Sort?,
    var unpaged: Boolean?
)