package com.example.lifehacktest.presentation.companyList

import android.app.Application
import androidx.lifecycle.*
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