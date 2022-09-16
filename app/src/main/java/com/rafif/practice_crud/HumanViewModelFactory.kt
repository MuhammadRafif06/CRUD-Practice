package com.rafif.practice_crud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafif.practice_crud.repository.HumanRepository
import java.lang.IllegalArgumentException

class HumanViewModelFactory(private val repository: HumanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HumanViewModel::class.java)){
            return HumanViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}