package com.spendTogether.models.ExpenseResponse

data class ExpenseResponseItem(
    val id: String,
    val name: String,
    val quantity:String,
    val paidBy:String,
    val date: String,
    val idGroup: String
)
