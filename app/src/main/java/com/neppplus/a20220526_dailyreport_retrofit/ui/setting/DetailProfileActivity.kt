package com.neppplus.a20220526_dailyreport_retrofit.ui.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.a20220526_dailyreport_retrofit.BaseActivity
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivityDetailProfileBinding
import com.neppplus.a20220526_dailyreport_retrofit.dialog.CustomAlertDialog
import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import com.neppplus.a20220526_dailyreport_retrofit.ui.main.LoginActivity
import com.neppplus.a20220526_dailyreport_retrofit.utils.ContextUtil
import com.neppplus.a20220526_dailyreport_retrofit.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProfileActivity : BaseActivity() {

    lateinit var binding : ActivityDetailProfileBinding

    var loginUser = GlobalData.loginUser!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_profile)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.logoutBtn.setOnClickListener {

            ContextUtil.clear(mContext)

            val myIntent = Intent(mContext, LoginActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(myIntent)
        }

        binding.changeNickBtn.setOnClickListener {

//            [도전과제]
//            제목, 내용물, 버튼 등등.....
//            인터페이스로 제작 가능하지 않을까?

            val alert = CustomAlertDialog(mContext, this)
            alert.myDialog(
                "닉네입 변경",
                "변경할 닉네임을 입력해 주세요.",
                false,
                object : CustomAlertDialog.ButtonClickListener{
                    override fun positiveBtnClicked() {

                        val changedNick = alert.binding.contentEdt.text.toString()

                        if (changedNick.isBlank()) {
                            Toast.makeText(mContext, "닉네임은 공백일 수 없습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            apiList.patchRequestEditUser(
                                ContextUtil.getLoginUserToken(mContext),
                                changedNick,
                                null,
                                null
                            ).enqueue(object : Callback<BasicResponse>{
                                override fun onResponse(
                                    call: Call<BasicResponse>,
                                    response: Response<BasicResponse>
                                ) {
                                    if (response.isSuccessful) {

                                        val br = response.body()!!

                                        GlobalData.loginUser = br.data.user
                                        binding.nicknameTxt.text = br.data.user.nick_name

                                        Toast.makeText(mContext, "닉네임 변경이 완료되었습니다.", Toast.LENGTH_SHORT)
                                            .show()

                                        alert.dialog.dismiss()
                                    }
                                    else {
                                        val jsonObj = JSONObject(response.errorBody()!!.string())
                                        val code = jsonObj.getInt("code")
                                        val message = jsonObj.getString("message")

                                        if (code == 400) {
                                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                                        }
                                        else {
                                            Log.e("${TAG}_회원 정보 수정 에러 ", message)
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                                }
                            })
                        }
                    }

                    override fun negetiveBtnClicked() {
                        alert.dialog.dismiss()
                    }
                }
            )
        }
    }

    override fun setValues() {
        Glide.with(mContext)
            .load(loginUser.profile_img)
            .into(binding.profileImg)
        binding.emailTxt.text = loginUser.email
        binding.nicknameTxt.text = loginUser.nick_name
    }
}