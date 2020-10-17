package com.android.companieshousestreaming

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.companieshousestreaming.databinding.ListItemBinding
import com.android.companieshousestreaming.models.JsonResponse

class RecyclerViewAdapter(private var context: Context) :
    PagingDataAdapter<JsonResponse.Data, RecyclerViewAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val listItemBinding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val companyDetail: JsonResponse.Data? = getItem(position)
        viewHolder.companyName.text = companyDetail!!.companyName
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<JsonResponse.Data>() {

            override fun areItemsTheSame(
                oldItem: JsonResponse.Data,
                newItem: JsonResponse.Data
            ): Boolean = oldItem.companyNumber == newItem.companyNumber

            override fun areContentsTheSame(
                oldItem: JsonResponse.Data,
                newItem: JsonResponse.Data
            ): Boolean = oldItem == newItem

        }
    }

    inner class ViewHolder(listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        val companyName: TextView = listItemBinding.companyName
    }
}