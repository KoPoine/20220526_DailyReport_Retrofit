package com.neppplus.a20220526_dailyreport_retrofit.models

class UserData (
    val id : Int,
    val email : String,
    val nick_name : String,
    val profile_img : String,
    val groups : List<GroupData>
        ) {
}