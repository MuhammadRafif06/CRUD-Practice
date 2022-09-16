package com.rafif.practice_crud.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "human_data_table")
data class Human (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "human_id")
    var id: Int,

    @ColumnInfo(name = "human_name")
    var name: String
)