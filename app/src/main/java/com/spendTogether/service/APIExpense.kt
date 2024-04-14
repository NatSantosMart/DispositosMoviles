package com.spendTogether.service;
import com.spendTogether.models.ExpenseResponse.ExpenseResponse
import com.spendTogether.models.ExpenseResponse.ExpenseResponseItem
import com.spendTogether.models.GroupResponse.GroupResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url;

interface ApiExpense {
    @GET
    suspend fun getExpenses(@Url url:String) : ExpenseResponse
    @GET("expenses/{groupId}")
    suspend fun getExpensesByGroup(@Path("groupId") groupId: String) : ExpenseResponse
    @POST
    suspend fun addExpense(@Url url:String, @Body newExpense: ExpenseResponseItem)
}
object RetrofitExpenseServiceFactory{

    fun getApiService(): ApiExpense{
        return Retrofit.Builder().
        baseUrl("http://192.168.0.11:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiExpense::class.java)
    }
}

