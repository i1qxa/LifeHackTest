package com.example.lifehacktest.presentation.companyList

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lifehacktest.R
import com.example.lifehacktest.data.remote.CompanyShort
import com.example.lifehacktest.data.remote.RetrofitService

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
            Glide.with(itemView)
                .load("${RetrofitService.BASE_URL}${item.img}")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_loading_img)
                .circleCrop()
                .into(companyLogo)
            tvCompanyName.text = item.name
            }
            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(item.id)
            }
        }

}