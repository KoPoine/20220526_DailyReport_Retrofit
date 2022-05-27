package com.neppplus.a20220526_dailyreport_retrofit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import com.neppplus.a20220526_dailyreport_retrofit.utils.ContextUtil
import com.neppplus.a20220526_dailyreport_retrofit.utils.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    var isTokenOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        토큰의 유효성 검사
//        1) 서버에 토큰으로 로그인 할 수 있는 API가 있는가?
//        2) 파라미터로 토큰만 받아서 접속 할 수 있는가?
//        3) 로그인시 내려주는 response와 비슷한 response를 내려주는가?
        apiList.getRequestMainInfo(
            ContextUtil.getLoginUserToken(mContext))
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!
                        isTokenOk = true
                        GlobalData.loginUser = br.data.user
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({
            val myIntent : Intent

            val isAutoLoginOk = ContextUtil.getAutoLogin(mContext)

            if (isAutoLoginOk && isTokenOk) {
                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
                myIntent = Intent(mContext, LoginActivity::class.java)
            }

            startActivity(myIntent)
            finish()


        }, 1500)
    }
}