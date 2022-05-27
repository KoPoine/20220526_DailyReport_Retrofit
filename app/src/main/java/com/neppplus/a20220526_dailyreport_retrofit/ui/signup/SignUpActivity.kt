package com.neppplus.a20220526_dailyreport_retrofit.ui.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.a20220526_dailyreport_retrofit.BaseActivity
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivitySignUpBinding
import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.signUpBtn.setOnClickListener {
            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()
            val inputNick = binding.nicknameEdt.text.toString()

            Log.d("${TAG}_email", inputEmail)
            Log.d("pw", inputPw)
            Log.d("nick", inputNick)

            apiList.putRequestSignUp(
                inputEmail,
                inputPw,
                inputNick).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {

                        val br = response.body()!!

                        Toast.makeText(mContext, "${br.data.user.nick_name}님 가입을 환영합니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        val errorBody = response.errorBody()!!
                        Log.d("에러 응답", response.toString())

                        val jsonObject = JSONObject(errorBody.string())
                        val message = jsonObject.getString("message")

                        Log.d("가입 에러", message)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }
    }

    override fun setValues() {

    }

}