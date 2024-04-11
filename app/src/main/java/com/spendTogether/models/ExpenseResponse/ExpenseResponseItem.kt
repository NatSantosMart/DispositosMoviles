package com.spendTogether.models.ExpenseResponse

data class ExpenseResponseItem(
    val id: String,
    val idGroup: Int,
    val name: String,
    val quantity:String,
    val date: String,
    val paidBy:String
)
