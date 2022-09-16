package com.rafif.practice_crud

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafif.practice_crud.db.Human
import com.rafif.practice_crud.repository.HumanRepository
import kotlinx.coroutines.launch

class HumanViewModel(private val repository: HumanRepository):
    ViewModel() {

    val inputText = MutableLiveData<String?>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllorDeleteButtonText = MutableLiveData<String>()

    val human = repository.human

    private lateinit var humanToUpdateOrDelete: Human

    private var isUpdateOrDelete = false

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllorDeleteButtonText.value = "Clear All"
    }

    fun initUpdateAndDelete(human: Human){
        inputText.value = human.name
        isUpdateOrDelete = true
        humanToUpdateOrDelete = human
        saveOrUpdateButtonText.value = "Update"
        clearAllorDeleteButtonText.value = "Clear"
    }

    fun saveOrUpdate(){
        if (isUpdateOrDelete){
            humanToUpdateOrDelete.name = inputText.value!!
            update(humanToUpdateOrDelete)
        }else{
            val name = inputText.value!!
            insert(Human(0, name))
            inputText.value = ""
        }
    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(humanToUpdateOrDelete)
        }else{
            clearAll()
        }
    }
    fun insert(human: Human){
        viewModelScope.launch {
            repository.insert(human)
        }
    }
    fun delete(human: Human){
        viewModelScope.launch {
            repository.delete(human)

            inputText.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "save"
            clearAllorDeleteButtonText.value = "Clear All"
        }
    }
    fun update(human: Human){
        viewModelScope.launch {
            repository.update(human)

            inputText.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "save"
            clearAllorDeleteButtonText.value = "Clear All"
        }
    }
    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}