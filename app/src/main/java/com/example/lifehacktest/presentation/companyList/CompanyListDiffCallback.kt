package com.example.lifehacktest.presentation.companyList

import androidx.recyclerview.widget.DiffUtil
import com.example.lifehacktest.data.remote.CompanyShort

class CompanyListDiffCallback:DiffUtil.ItemCallback<CompanyShort>() {
    override fun areItemsTheSame(oldItem: CompanyShort, newItem: CompanyShort): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CompanyShort, newItem: CompanyShort): Boolean {
        return oldItem == newItem
    }
}