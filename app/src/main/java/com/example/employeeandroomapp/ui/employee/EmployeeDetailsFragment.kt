package com.example.employeeandroomapp.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.employeeandroomapp.databinding.FragmentEmployeeDetailsBinding
import com.example.employeeandroomapp.ui.base.BaseFragment

class EmployeeDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentEmployeeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEmployeeDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val empId = EmployeeDetailsFragmentArgs.fromBundle(requireArguments()).empId

        mainViewModel.setId(empId)

        mainViewModel.employee.observe(viewLifecycleOwner, Observer {
            binding.employee = it
        })

    }
}