package com.neppplus.a20220526_dailyreport_retrofit.models

data class DataResponse (
        val user : UserData,
        val token : String,
        val total_goal_seconds : Int,
        ) {
}