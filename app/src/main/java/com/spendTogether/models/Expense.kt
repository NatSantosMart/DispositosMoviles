package com.spendTogether.models

import java.util.Date

data class Expense (var id:Int, var idGroup:Int, var name:String, var quantity:Float, var date:Date, var paidBy:String,var involved: ArrayList<String>)