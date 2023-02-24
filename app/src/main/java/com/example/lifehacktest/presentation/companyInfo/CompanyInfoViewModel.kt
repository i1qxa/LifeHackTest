package com.example.lifehacktest.presentation.companyInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifehacktest.data.remote.Company
import com.example.lifehacktest.data.remote.RetrofitService
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CompanyInfoViewModel : ViewModel() {

    private val retrofit = RetrofitService.getInstance()

    private val _companyLD = MutableLiveData<Company?>()
    val companyLD: LiveData<Company?>
        get() = _companyLD

    fun getCompany(companyId: Int) {
        if (companyId == EMPTY_COMPANY_ID) throw java.lang.RuntimeException("Param company id is empty")
        else {
            viewModelScope.launch {
                val response = retrofit.getCompanyInfo(companyId)
                if (response.isSuccessful) {
                    _companyLD.postValue(customSerializer(response.body()))
                } else {
                    _companyLD.postValue(null)
                }
            }
        }
    }

    private fun customSerializer(response: String?): Company? {
        if (response == null) return null
        return try {
            Json.decodeFromString<Company>(response.substring(1 until response.length-1))
        } catch (e:java.lang.Exception){
            null
        }
    }
}