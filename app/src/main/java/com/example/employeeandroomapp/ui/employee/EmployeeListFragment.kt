package com.example.employeeandroomapp.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeeandroomapp.databinding.FragmentEmployeeListBinding
import com.example.employeeandroomapp.ui.base.BaseFragment
import com.example.employeeandroomapp.ui.viewmodel.MainViewModel
import com.example.employeeandroomapp.utils.autoCleared

class EmployeeListFragment : BaseFragment() {

    private lateinit var binding: FragmentEmployeeListBinding
    var adapter by autoCleared<EmployeeListAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEmployeeListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this,providerFactory).get(MainViewModel::class.java)

        binding.viewModel = mainViewModel

        binding.empListRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val rvadapter =
            EmployeeListAdapter(
                appExecutors
            ) { employee ->

                findNavController().navigate(
                    EmployeeListFragmentDirections.showEmployeeDetails(
                        employee.id
                    )
                )

            }

        binding.empListRecyclerView.adapter = rvadapter
        adapter = rvadapter
        mainViewModel.results.observe(viewLifecycleOwner, Observer {

            binding.searchStatus = it
            binding.resultCount = it.data?.size ?: 0
            adapter.submitList(it.data)
        })

    }
}