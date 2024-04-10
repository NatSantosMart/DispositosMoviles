package com.spendTogether.models.GroupResponse

data class GroupResponseItem(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val participants: List<String>
)
