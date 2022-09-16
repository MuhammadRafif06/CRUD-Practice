package com.rafif.practice_crud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafif.practice_crud.R
import com.rafif.practice_crud.databinding.ListItemBinding
import com.rafif.practice_crud.db.Human

class MyRecyclerViewAdapter(
    private val humanList: List<Human>,
    private val clickListener : (Human) -> Unit
)
    : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(humanList[position], clickListener)
    }

    override fun getItemCount(): Int = humanList.size
}

class MyViewHolder (val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(human: Human, clickListener: (Human) -> Unit){
        binding.nameTv.text = human.name
        binding.listItemLayout.setOnClickListener {
            clickListener(human)
        }
    }
}


