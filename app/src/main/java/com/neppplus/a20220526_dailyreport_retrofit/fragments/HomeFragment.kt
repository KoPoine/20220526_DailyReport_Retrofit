package com.neppplus.a20220526_dailyreport_retrofit.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.adapters.MainRecyclerViewAdapter
import com.neppplus.a20220526_dailyreport_retrofit.databinding.FragmentHomeBinding
import com.neppplus.a20220526_dailyreport_retrofit.models.BasicResponse
import com.neppplus.a20220526_dailyreport_retrofit.models.GroupData
import com.neppplus.a20220526_dailyreport_retrofit.ui.goal.AddGoalActivity
import com.neppplus.a20220526_dailyreport_retrofit.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var mGroupAdapter : MainRecyclerViewAdapter

    var groupList = ArrayList<GroupData>()

    var totalGoalSecond = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()
        getDataFromServer()
    }

    override fun setupEvents() {
        binding.addGoalBtn.setOnClickListener {
            val myIntent = Intent(mContext, AddGoalActivity::class.java)
            startActivity(myIntent)
        }

        binding.faBtn.setOnClickListener {
            val myIntent = Intent(mContext, AddGoalActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        val myCal = Calendar.getInstance()
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DATE)

        binding.titleTxt.text = "${month+1}월 ${day}일"

        if (totalGoalSecond == 0) {
            binding.emptyLayout.visibility = View.VISIBLE
            binding.mainRecyclerView.visibility = View.GONE
        }
        else {
            binding.emptyLayout.visibility = View.GONE
            binding.mainRecyclerView.visibility = View.VISIBLE
        }
    }

    fun getDataFromServer() {
        apiList.getRequestMainInfo(
            ContextUtil.getLoginUserToken(mContext)
        ).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    totalGoalSecond = br.data.total_goal_seconds

                    if (totalGoalSecond > 0) {

                        if (groupList.size != 0) {
                            groupList.clear()
                        }

                        groupList.addAll(br.data.user.groups)
                        initAdapters()


                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    fun initAdapters () {
        mGroupAdapter = MainRecyclerViewAdapter(mContext, groupList)
        binding.mainRecyclerView.adapter = mGroupAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(mContext)

        mGroupAdapter.notifyDataSetChanged()
    }


}