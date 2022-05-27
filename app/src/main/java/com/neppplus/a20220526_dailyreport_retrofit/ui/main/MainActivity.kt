package com.neppplus.a20220526_dailyreport_retrofit.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.neppplus.a20220526_dailyreport_retrofit.BaseActivity
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.adapters.MainViewPagerAdapter
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mPagerAdapter : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mPagerAdapter = MainViewPagerAdapter(this)
        binding.mainViewPager.adapter = mPagerAdapter

        binding.mainViewPager.offscreenPageLimit = 5
        binding.mainViewPager.currentItem = 2
        binding.bottomNav.selectedItemId = R.id.home

        binding.mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.feed -> binding.mainViewPager.currentItem = 0
                R.id.stat -> binding.mainViewPager.currentItem = 1
                R.id.home -> binding.mainViewPager.currentItem = 2
                R.id.timeTable -> binding.mainViewPager.currentItem = 3
                R.id.setting -> binding.mainViewPager.currentItem = 4
            }
            return@setOnItemSelectedListener true
        }

    }
}

















