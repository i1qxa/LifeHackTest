package com.example.lifehacktest.presentation.companyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifehacktest.data.remote.CompanyShort
import com.example.lifehacktest.data.remote.RetrofitService
import kotlinx.coroutines.launch

class CompanyListViewModel : ViewModel() {

    private val retrofitService = RetrofitService.getInstance()

    private val _companyList = MutableLiveData<List<CompanyShort>>()
    val companyList: LiveData<List<CompanyShort>>
        get() = _companyList

    init {
        viewModelScope.launch {
            val response = retrofitService.getCompanyList()
            if (response.isSuccessful) {
                _companyList.postValue(response.body())
            }
        }
    }

}