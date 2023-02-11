package com.example.lifehacktest.presentation.companyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lifehacktest.R
import com.example.lifehacktest.data.remote.CompanyShort
import com.example.lifehacktest.data.remote.RetrofitService
import com.squareup.picasso.Picasso

class CompanyListRVAdapter :
    ListAdapter<CompanyShort, CompanyListViewHolder>(CompanyListDiffCallback()) {

    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = R.layout.company_item
        return CompanyListViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompanyListViewHolder, position: Int) {
            val item = getItem(position)
            with(holder){
                Picasso.get().load("${RetrofitService.BASE_URL}${item.img}").into(companyLogo)
                tvCompanyName.text = item.name
            }
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(item.id)
            }
        }

}