package com.neppplus.a20220526_dailyreport_retrofit.api

import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIList {

    //    user
    @FormUrlEncoded
    @PATCH("/user")
    fun patchRequestEditUser(
        @Header("X-Http-Token") token: String,
        @Field("nick_name") nickname: String?,
        @Field("current_password") currentPw: String?,
        @Field("new_password") newPw: String?,
    ): Call<BasicResponse>

    @FormUrlEncoded  // 파라미터 중에 폼데이터로 담아야하는 파라미터가 있다면 필요한 구문
    @POST("/user")  // retrofit이 POST라는 메쏘드로 적는구나
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String
    ): Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nick: String
    ): Call<BasicResponse>

    //    goal
    @FormUrlEncoded
    @POST("/goal")
    fun postRequestAddGoal(
        @Header("X-Http-Token") token : String,
        @Field("title") title : String,
        @Field("color_value") color_value : String,
        @Field("group_id") group_id : String,
        @Field("goal_hour") goal_hour : String,
        @Field("goal_minute") goal_minute : String,
    ): Call<BasicResponse>

    //    main
    @GET("/main_info")
    fun getRequestMainInfo(
        @Header("X-Http-Token") token: String
    ): Call<BasicResponse>


}