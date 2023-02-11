package com.example.lifehacktest.presentation.companyList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifehacktest.R

class CompanyListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val companyLogo = itemView.findViewById<ImageView>(R.id.ivCompanyLogo)
    val tvCompanyName = itemView.findViewById<TextView>(R.id.tvCompanyName)
}