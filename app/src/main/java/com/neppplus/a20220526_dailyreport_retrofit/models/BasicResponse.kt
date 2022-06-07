package com.neppplus.a20220526_dailyreport_retrofit.models

data class BasicResponse (
    val code : Int,
    val message : String,
    val data : DataResponse,
        ) {
}