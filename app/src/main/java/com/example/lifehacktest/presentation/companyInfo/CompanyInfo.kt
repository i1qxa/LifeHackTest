package com.example.lifehacktest.presentation.companyInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifehacktest.data.remote.Company
import com.example.lifehacktest.data.remote.RetrofitService
import com.example.lifehacktest.databinding.FragmentCompanyInfoBinding
import com.squareup.picasso.Picasso

private const val COMPANY_ID = "company_id"
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
        var a = it
        //initViews(it)
        }
    }

    private fun initViews(company: Company) {
        binding.tvCompanyName.text = company.name
        Picasso.get().load("${RetrofitService.BASE_URL}${company.img}").into(binding.ivCompanyLogo)
        binding.tvCompanyDescription.text = company.description
        binding.tvCoordinate.text = "${company.lat} : ${company.lon}"
        binding.tvWebSite.text = company.vebSite?:""
        binding.tvPfoneNumber.text = company.phoneNumber?:""
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