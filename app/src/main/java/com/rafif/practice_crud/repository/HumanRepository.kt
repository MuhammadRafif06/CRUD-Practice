package com.rafif.practice_crud.repository

import com.rafif.practice_crud.db.Human
import com.rafif.practice_crud.db.HumanDAO

class HumanRepository(private val dao: HumanDAO) {

    val human = dao.getAllHuman()

    suspend fun insert(human: Human){
        dao.insertHuman(human)
    }
    suspend fun update(human: Human){
        dao.updateHuman(human)
    }
    suspend fun delete(human: Human){
        dao.deleteHuman(human)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}