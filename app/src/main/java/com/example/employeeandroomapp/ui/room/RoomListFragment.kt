package com.example.employeeandroomapp.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeeandroomapp.databinding.FragmentRoomListBinding
import com.example.employeeandroomapp.ui.base.BaseFragment
import com.example.employeeandroomapp.ui.viewmodel.MainViewModel
import com.example.employeeandroomapp.utils.autoCleared

class RoomListFragment : BaseFragment() {

    private var adapter by autoCleared<RoomsListAdapter>()

    private lateinit var binding : FragmentRoomListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRoomListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this,providerFactory).get(MainViewModel::class.java)

        binding.viewModel = mainViewModel

        binding.roomListRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val rvadapter =
            RoomsListAdapter(appExecutors)
        binding.roomListRecyclerView.adapter = rvadapter
        adapter = rvadapter

        mainViewModel.roomResults.observe(viewLifecycleOwner, Observer {
            binding.searchStatus = it
            binding.resultCount = it?.data?.size ?: 0
            adapter.submitList(it.data)
        })
    }

    companion object {
        private const val TAG = "RoomListFragment"
    }
}