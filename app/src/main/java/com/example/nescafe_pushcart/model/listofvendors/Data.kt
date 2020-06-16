package com.example.nescafe_pushcart.model.listofvendors


import com.google.gson.annotations.SerializedName

data class Data(
    var content: List<Content?>?,
    var empty: Boolean?,
    var first: Boolean?,
    var last: Boolean?,
    var number: Int?,
    var numberOfElements: Int?,
    var pageable: Pageable?,
    var size: Int?,
    var sort: SortX?,
    var totalElements: Int?,
    var totalPages: Int?
)