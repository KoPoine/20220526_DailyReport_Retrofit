package com.neppplus.a20220526_dailyreport_retrofit.models

data class GroupData (
    val id : Int,
    val name : String,
    val goals : List<GoalData>
        ) {
}