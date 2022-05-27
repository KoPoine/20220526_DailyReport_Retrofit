package com.neppplus.a20220526_dailyreport_retrofit.ui.goal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.neppplus.a20220526_dailyreport_retrofit.BaseActivity
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivityAddGoalBinding
import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import com.neppplus.a20220526_dailyreport_retrofit.utils.ContextUtil
import com.neppplus.a20220526_dailyreport_retrofit.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGoalActivity : BaseActivity() {

    lateinit var binding : ActivityAddGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_goal)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        customBinding.saveBtn.setOnClickListener {
            val goalTitle = binding.titleEdt.text.toString()
            apiList.postRequestAddGoal(
                ContextUtil.getLoginUserToken(mContext),
                goalTitle,
                "#4FC3F7",
                GlobalData.loginUser!!.groups[0].id.toString(),
                "1",
                "00"
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {

                    }
                    else {
                        val jsonObj = JSONObject(response.errorBody()!!.string())
                        val code = jsonObj.getInt("code")
                        val message = jsonObj.getString("message")

                        Log.e("${TAG}서버에러 code", code.toString())
                        Log.e("${TAG}서버에러 message", message)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })
        }
    }

    override fun setValues() {
        customBinding.saveBtn.visibility = View.VISIBLE
        customBinding.titleTxt.text = "목표 추가"
    }
}