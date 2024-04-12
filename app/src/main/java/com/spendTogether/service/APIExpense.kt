package com.spendTogether.service;
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url;

interface ApiExpense {
    @POST
    suspend fun addExpense(@Url url:String, @Body newExpense: ExpenseResponseItem)
}
object RetrofitExpenseServiceFactory{

    fun getApiService(): ApiExpense{
        return Retrofit.Builder().
        baseUrl("http://192.168.1.16:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiExpense::class.java)
    }
}

