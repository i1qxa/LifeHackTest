package com.example.lifehacktest.presentation.companyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifehacktest.R
import com.example.lifehacktest.databinding.FragmentCompanyListBinding
import com.example.lifehacktest.presentation.companyInfo.CompanyInfo

class CompanyList : Fragment() {

    private lateinit var binding: FragmentCompanyListBinding
    private lateinit var viewModel: CompanyListViewModel
    private lateinit var rvAdapter: CompanyListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = CompanyListViewModel()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        rvAdapter = CompanyListRVAdapter()
        rvAdapter.onItemClickListener = {
            launchCompanyInfoFragment(CompanyInfo.newInstance(it))
        }
        with(binding.rvCompanyList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeViewModel() {
        viewModel.companyList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    private fun launchCompanyInfoFragment(fragment: CompanyInfo) {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = CompanyList()
    }
}