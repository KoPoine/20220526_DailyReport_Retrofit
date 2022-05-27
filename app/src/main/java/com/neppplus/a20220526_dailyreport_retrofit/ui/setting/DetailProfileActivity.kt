package com.neppplus.a20220526_dailyreport_retrofit.ui.setting

import android.os.Bundle
import com.neppplus.a20220526_dailyreport_retrofit.BaseActivity
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivityDetailProfileBinding

class DetailProfileActivity : BaseActivity() {

    lateinit var binding : ActivityDetailProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profile)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}