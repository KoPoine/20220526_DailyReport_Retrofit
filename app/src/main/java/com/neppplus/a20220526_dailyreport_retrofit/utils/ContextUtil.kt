package com.neppplus.a20220526_dailyreport_retrofit.utils

import android.content.Context

class ContextUtil {

    companion object {

        private val prefName = "DailyReport"

        private val AUTO_LOGIN = "AUTO_LOGIN"
        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

        fun setAutoLogin ( context : Context , isAutoLogin : Boolean ) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, isAutoLogin).apply()
        }

        fun getAutoLogin ( context: Context ) : Boolean {
            val  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)
        }

        fun setLoginUserToken ( context: Context, token : String ) {
            val  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        fun getLoginUserToken ( context: Context ) : String {
            val  pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }
    }

}