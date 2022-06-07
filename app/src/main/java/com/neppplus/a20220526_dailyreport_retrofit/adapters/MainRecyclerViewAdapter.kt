package com.neppplus.a20220526_dailyreport_retrofit.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ListItemGoalBinding
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ListItemGroupBinding
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ListItemHeaderBinding
import com.neppplus.a20220526_dailyreport_retrofit.databinding.ListItemItemBinding
import com.neppplus.a20220526_dailyreport_retrofit.models.GoalData
import com.neppplus.a20220526_dailyreport_retrofit.models.GroupData

class MainRecyclerViewAdapter(val mContext : Context, val mList : List<GroupData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_HEADER = 0
    val TYPE_ITEM = 1

    lateinit var frag : Fragment

    lateinit var conAdapter : ConViewPagerAdapter
    lateinit var itemBinding: ListItemGroupBinding
    lateinit var goalBinding : ListItemGoalBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                HeaderViewHolder(ListItemHeaderBinding.inflate(LayoutInflater.from(mContext), parent, false))
            }
            else -> {
                ItemViewHolder(ListItemGroupBinding.inflate(LayoutInflater.from(mContext), parent, false))
            }
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bindPage()
            }
            is ItemViewHolder -> {
                holder.bind(mList[position-1])
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 ->  TYPE_HEADER
            else -> TYPE_ITEM
        }
    }

    inner class HeaderViewHolder(val binding : ListItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindPage() {
            conAdapter = ConViewPagerAdapter(frag)
            binding.conPager.adapter =conAdapter
            TabLayoutMediator(binding.conTab, binding.conPager) { tab , position ->
                tab.text = when (position) {
                    0 -> "집중한 시간"
                    else -> "남은 시간"
                }
            }.attach()
        }
    }

    inner class ItemViewHolder(val binding : ListItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GroupData) {
            binding.groupNameTxt.text = item.name

            binding.goalLinearLayout.removeAllViews()
            for (goal in item.goals) {
                goalBinding = ListItemGoalBinding.inflate(LayoutInflater.from(mContext))
                binding.goalLinearLayout.addView(goalBinding.root)
                goalBinding.goalNameTxt.text = goal.title
            }
        }
    }
}