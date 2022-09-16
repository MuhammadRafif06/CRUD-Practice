package com.rafif.practice_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafif.practice_crud.adapter.MyRecyclerViewAdapter
import com.rafif.practice_crud.databinding.ActivityMainBinding
import com.rafif.practice_crud.db.Human
import com.rafif.practice_crud.db.HumanDataBase
import com.rafif.practice_crud.repository.HumanRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var humanViewModel: HumanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = HumanDataBase.getInstance(application).humanDAO
        val repository = HumanRepository(dao)
        val factory = HumanViewModelFactory(repository)

        humanViewModel = ViewModelProvider(this, factory)[HumanViewModel::class.java]

        binding.myViewModel = humanViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.humanRecyclerView.layoutManager = LinearLayoutManager(this)
        displayHumanList()
    }
    private fun displayHumanList(){
        humanViewModel.human.observe(this, Observer {
            Log.i("tag", "displayHumanList: $it")
            binding.humanRecyclerView.adapter = MyRecyclerViewAdapter(it, { selectedItem: Human -> listItemClicked(selectedItem) })
        })
    }
    private fun listItemClicked(human: Human){
        humanViewModel.initUpdateAndDelete(human)
    }
}