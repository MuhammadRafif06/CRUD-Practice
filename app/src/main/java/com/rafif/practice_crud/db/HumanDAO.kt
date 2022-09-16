package com.rafif.practice_crud.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HumanDAO {

    @Insert
    suspend fun insertHuman(human: Human) : Long

    @Update
    suspend fun updateHuman(human: Human)

    @Delete
    suspend fun deleteHuman(human: Human)

    @Query("DELETE FROM human_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM human_data_table")
    fun getAllHuman() : LiveData<List<Human>>
}