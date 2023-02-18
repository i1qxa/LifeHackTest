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

    private val _companyLD = MutableLiveData<Company>()
    val companyLD: LiveData<Company>
        get() = _companyLD

    fun getCompany(companyId: Int) {
        if (companyId == EMPTY_COMPANY_ID) throw java.lang.RuntimeException("Param company id is empty")
        else {
            viewModelScope.launch {
                val response = retrofit.getCompanyInfo(companyId)
                if (response.isSuccessful){
                    val responseBody = response.body().toString()
                    Log.i("Test Response", responseBody)
                    _companyLD.postValue(customSerializer(responseBody))
                }
            }
        }
    }
private fun customSerializer(response:String):Company{
    var modifiedResponse = Regex(""" "[^,]""").replace(response," '")
    modifiedResponse = Regex("""" """).replace(modifiedResponse, "' ")
    modifiedResponse = modifiedResponse.substring(1 until modifiedResponse.length-1)
    Log.i("Test Response", modifiedResponse)
    return Json.decodeFromString<Company>(modifiedResponse)
    }
}