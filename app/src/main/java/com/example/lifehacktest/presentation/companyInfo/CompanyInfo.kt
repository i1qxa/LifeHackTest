package com.example.lifehacktest.presentation.companyInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lifehacktest.data.remote.Company
import com.example.lifehacktest.data.remote.RetrofitService
import com.example.lifehacktest.databinding.FragmentCompanyInfoBinding

private const val COMPANY_ID = "company"
const val EMPTY_COMPANY_ID = -1

class CompanyInfo : Fragment() {
    private var companyId: Int? = null
    private lateinit var binding: FragmentCompanyInfoBinding
    private lateinit var viewModel: CompanyInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyInfoBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = CompanyInfoViewModel()
        viewModel.getCompany(companyId?: EMPTY_COMPANY_ID)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.companyLD.observe(viewLifecycleOwner) {
            initViews(it)
        }
    }

    private fun initViews(company: Company?) {
        if (company!=null){
            Glide.with(binding.ivCompanyLogo)
                .load("${RetrofitService.BASE_URL}${company.img}")
                .centerCrop()
                .into(binding.ivCompanyLogo)
            with(binding){
                scrollViewCompany.visibility = View.VISIBLE
                tvCompanyName.text = company.name
                tvCompanyDescription.text = company.description
                tvCoordinate.text = "${company.lat},${company.lon}"
                tvWeb.text = company.www?:""
                tvPhoneNumber.text = company.phone?:""
            }

        }
        else{
            Toast.makeText(requireContext(),"Ошибка загрузки данных",Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
    }


    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(COMPANY_ID)) throw java.lang.RuntimeException("Param Company Id is absent")
        else companyId = args.getInt(COMPANY_ID)
    }

    companion object {
        @JvmStatic
        fun newInstance(companyId: Int) =
            CompanyInfo().apply {
                arguments = Bundle().apply {
                putInt(COMPANY_ID, companyId)
                }
            }
    }
}