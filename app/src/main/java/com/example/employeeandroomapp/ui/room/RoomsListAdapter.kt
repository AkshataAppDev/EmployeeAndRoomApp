package com.example.employeeandroomapp.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.employeeandroomapp.R
import com.example.employeeandroomapp.databinding.ListviewItemRoomBinding
import com.example.employeeandroomapp.models.Rooms
import com.example.employeeandroomapp.repository.util.AppExecutors
import com.example.employeeandroomapp.ui.base.DataBoundListAdapter

class RoomsListAdapter(
    appExecutors: AppExecutors
) : DataBoundListAdapter<Rooms, ListviewItemRoomBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Rooms>() {
        override fun areItemsTheSame(oldItem: Rooms, newItem: Rooms): Boolean {
            return oldItem.id == newItem.id && oldItem.roomName == newItem.roomName
        }

        override fun areContentsTheSame(oldItem: Rooms, newItem: Rooms): Boolean {
            return oldItem.id == newItem.id && oldItem.roomName == newItem.roomName
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ListviewItemRoomBinding {
        val binding = DataBindingUtil.inflate<ListviewItemRoomBinding>(
            LayoutInflater.from(parent.context),
            R.layout.listview_item_room,
            parent,
            false
        )

        return binding
    }

    override fun bind(binding: ListviewItemRoomBinding, item: Rooms) {
        binding.room = item
    }
}