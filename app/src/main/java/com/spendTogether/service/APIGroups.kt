package com.spendTogether.service;
import com.spendTogether.models.ExpenseResponse.ExpenseResponse
import com.spendTogether.models.GroupResponse.GroupResponse
import com.spendTogether.models.GroupResponse.GroupResponseItem
import okhttp3.internal.http.RetryAndFollowUpInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url;

interface ApiGroups {
    @GET
    suspend fun getGroups(@Url url:String) : GroupResponse

    @GET("groups/{groupId}")
    suspend fun getGroupById(@Path("groupId") groupId: String) : GroupResponseItem

    @POST
    suspend fun addGroup(@Url url:String, @Body newGroup: GroupResponseItem)
}

object RetrofitServiceFactory{

    fun getApiService(): ApiGroups{
        return Retrofit.Builder().
            baseUrl("http://192.168.0.11:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGroups::class.java)
    }
}


