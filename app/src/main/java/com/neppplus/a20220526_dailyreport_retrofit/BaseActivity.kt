package com.neppplus.a20220526_dailyreport_retrofit

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.neppplus.a20220526_dailyreport_retrofit.api.APIList
import com.neppplus.a20220526_dailyreport_retrofit.api.ServerAPI
import com.neppplus.a20220526_dailyreport_retrofit.databinding.CustomActionBarBinding

abstract class BaseActivity :AppCompatActivity() {

    lateinit var mContext : Context

//    모든 화면에서 apiList 변수가 있다면 => apiList.서버기능() 형태로 간단하게 코딩 가능
    lateinit var apiList : APIList

    val TAG = javaClass.simpleName

    lateinit var customBinding : CustomActionBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiList = retrofit.create(APIList::class.java)

        supportActionBar?.let {
            setCustomActionBar()
        }
    }

    abstract fun setupEvents()

    abstract fun setValues()

    fun setCustomActionBar() {
        val defaultActionBar = supportActionBar!!
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        val parent = this.window.decorView as ViewGroup
        customBinding = DataBindingUtil.inflate(this.layoutInflater, R.layout.custom_action_bar, parent, false)
        defaultActionBar.setCustomView(customBinding.root)

        val myToolbar = defaultActionBar.customView.parent as Toolbar
        myToolbar.setContentInsetsAbsolute(0,0)

        customBinding.backBtn.setOnClickListener { finish() }
        customBinding.closeBtn.setOnClickListener { finish() }
    }
}





















