package com.rafif.practice_crud.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Human::class], version = 1)
abstract class HumanDataBase : RoomDatabase() {

    abstract val humanDAO : HumanDAO

    companion object{
        @Volatile

        private var INSTANCE : HumanDataBase? = null

        fun getInstance(context: Context) : HumanDataBase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HumanDataBase::class.java,
                        "subreker_data.db"
                    ).build()
                }
                return instance
            }
        }
    }
}