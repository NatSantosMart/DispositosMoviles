package com.spendTogether.models

import java.util.Date

data class Group (var id:Int, var name:String, var description:String, var category:String, var date:Date, var participants: ArrayList<String>)