package com.example.lifehacktest.presentation.companyInfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifehacktest.data.remote.Company
import com.example.lifehacktest.data.remote.RetroLite
import com.example.lifehacktest.data.remote.RetrofitService
import com.google.gson.Gson
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

//class CompanyInfoViewModel : ViewModel() {
//
//    private val retrofit = RetroLite.getInstance()
//
//    private val _companyLD = MutableLiveData<Company>()
//    val companyLD: LiveData<Company>
//        get() = _companyLD
//
//    fun getCompany(companyId: Int) {
//        if (companyId == EMPTY_COMPANY_ID) throw java.lang.RuntimeException("Param company id is empty")
//        else{
//            viewModelScope.launch {
//                val response = retrofit.getCompanyInfo(companyId)
//                if (response.isSuccessful) _companyLD.postValue(response.body()?.get(0))
//            }
//        }
//    }
//
//}
class CompanyInfoViewModel : ViewModel() {

    private val retrofit = RetrofitService.getInstance()

    private val _companyLD = MutableLiveData<String>()
    val companyLD: LiveData<String>
        get() = _companyLD

    fun getCompany(companyId: Int) {
        if (companyId == EMPTY_COMPANY_ID) throw java.lang.RuntimeException("Param company id is empty")
        else {
            viewModelScope.launch {
                val response = retrofit.getCompanyInfo(companyId)
                if (response.isSuccessful) _companyLD.postValue(response.body())
                var a =response.body().toString()
                Log.i("Test response", a)
                customSerializer(a)
            }
        }
    }
private fun customSerializer(response:String){
    var a = Regex(""" """").replace(response," '")
    a = Regex("""" """).replace(a, "' ")
    a = a.substring(1 until a.length-1)
    Log.i("Test response", a)
    val gson = Gson()
    val e = gson.toJson(a)
    val p = gson.fromJson(e,Company::class.java)
    val b =1
}
}