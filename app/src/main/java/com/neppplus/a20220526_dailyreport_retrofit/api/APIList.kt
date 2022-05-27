package com.neppplus.a20220526_dailyreport_retrofit.api

import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIList {


    @FormUrlEncoded  // 파라미터 중에 폼데이터로 담아야하는 파라미터가 있다면 필요한 구문
    @POST("/user")  // retrofit이 POST라는 메쏘드로 적는구나
    fun postRequestLogin (
        @Field("email") email: String,
        @Field("password") pw: String
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp (
        @Field("email")email: String,
        @Field("password")pw :String,
        @Field("nick_name") nick : String
    ) : Call<BasicResponse>

    @GET("/main_info")
    fun getRequestMainInfo(
        @Header("X-Http-Token")token : String
    ) : Call<BasicResponse>
}