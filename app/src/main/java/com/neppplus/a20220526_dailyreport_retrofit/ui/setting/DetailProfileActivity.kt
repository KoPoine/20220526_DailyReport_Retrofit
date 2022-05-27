package com.neppplus.a20220526_dailyreport_retrofit.ui.setting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.a20220526_dailyreport_retrofit.BaseActivity
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivityDetailProfileBinding
import com.neppplus.a20220526_dailyreport_retrofit.dialog.CustomAlertDialog
import com.neppplus.a20220526_dailyreport_retrofit.ui.main.LoginActivity
import com.neppplus.a20220526_dailyreport_retrofit.utils.ContextUtil
import com.neppplus.a20220526_dailyreport_retrofit.utils.GlobalData

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
            val alert = CustomAlertDialog(mContext, this)
            alert.myDialog(
                "닉네입 변경",
                "변경할 닉네임을 입력해 주세요.",
                false,
                object : CustomAlertDialog.ButtonClickListener{
                    override fun positiveBtnClicked() {
                        val changedNick = alert.binding.contentEdt.text.toString()

                        Toast.makeText(mContext, changedNick, Toast.LENGTH_SHORT).show()
                    }

                    override fun negetiveBtnClicked() {

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