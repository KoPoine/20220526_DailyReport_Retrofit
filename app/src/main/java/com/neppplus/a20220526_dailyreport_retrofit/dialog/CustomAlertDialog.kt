package com.neppplus.a20220526_dailyreport_retrofit.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.neppplus.a20220526_dailyreport_retrofit.R
import com.neppplus.a20220526_dailyreport_retrofit.databinding.CustomAlertDialogBinding

class CustomAlertDialog( val mContext : Context, val activity: Activity) {

    private val dialog = Dialog(mContext)

    lateinit var binding : CustomAlertDialogBinding

    fun myDialog ( title : String, body : String, isDelete : Boolean, onClickListener : ButtonClickListener ) {
        binding = CustomAlertDialogBinding.inflate(activity.layoutInflater)
        dialog.setContentView(binding.root)

        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        dialog.setCancelable(true)

        binding.titleTxt.text = title
        binding.bodyTxt.text = body

        if (isDelete) {
            binding.positiveBtn.setBackgroundColor(ContextCompat.getColor(mContext, R.color.error_dark))
        }

        binding.positiveBtn.setOnClickListener {
            onClickListener.positiveBtnClicked()
            dialog.dismiss()
        }
        binding.negativeBtn.setOnClickListener {
            onClickListener.negetiveBtnClicked()
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun positiveBtnClicked()
        fun negetiveBtnClicked()
    }

}