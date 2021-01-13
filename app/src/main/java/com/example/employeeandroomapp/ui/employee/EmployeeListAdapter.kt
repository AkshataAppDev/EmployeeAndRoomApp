package com.example.employeeandroomapp.ui.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.employeeandroomapp.R
import com.example.employeeandroomapp.databinding.ListviewItemEmpBinding
import com.example.employeeandroomapp.models.Employee
import com.example.employeeandroomapp.repository.util.AppExecutors
import com.example.employeeandroomapp.ui.base.DataBoundListAdapter

class EmployeeListAdapter(
    appExecutors: AppExecutors,
    private val empClickCallBack: ((Employee) -> Unit)?
) : DataBoundListAdapter<Employee, ListviewItemEmpBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id && oldItem.firstName == newItem.firstName
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id && oldItem.firstName == newItem.firstName
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ListviewItemEmpBinding {
        val binding = DataBindingUtil.inflate<ListviewItemEmpBinding>(
            LayoutInflater.from(parent.context),
            R.layout.listview_item_emp,
            parent,
            false
        )

        binding.root.setOnClickListener {
            binding.employee?.let {
                empClickCallBack?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ListviewItemEmpBinding, item: Employee) {
        binding.employee = item
    }
}