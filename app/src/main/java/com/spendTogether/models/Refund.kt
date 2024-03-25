package com.spendTogether.models

data class Refund (var id:Int, var idGroup:Int, var quantity:Int, var personWhoPay: String, var personWhoReceive: String)