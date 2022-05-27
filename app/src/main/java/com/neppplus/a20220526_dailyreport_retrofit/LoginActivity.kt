package com.neppplus.a20220526_dailyreport_retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivityLoginBinding
import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import com.neppplus.a20220526_dailyreport_retrofit.utils.ContextUtil
import com.neppplus.a20220526_dailyreport_retrofit.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        binding.loginBtn.setOnClickListener {
            val editEmail = binding.emailEdt.text.toString()
            val editPw = binding.passwordEdt.text.toString()

            apiList.postRequestLogin(editEmail, editPw).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {

                        val br = response.body()!!

                        ContextUtil.setAutoLogin(mContext, binding.autoCheckBox.isChecked)
                        ContextUtil.setLoginUserToken(mContext, br.data.token)

                        GlobalData.loginUser = br.data.user

                        Toast.makeText(mContext, "${ br.data.user.nick_name }님 환영합니다.", Toast.LENGTH_SHORT).show()
                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                    }

                    else {
                        val errorBody = response.errorBody()!!

                        val jsonObject = JSONObject(errorBody.string())
                        val message = jsonObject.getString("message")
                        val code = jsonObject.getInt("code")

                        if (code == 400) {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

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