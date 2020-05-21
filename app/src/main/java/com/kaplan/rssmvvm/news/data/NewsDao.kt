package com.kaplan.rssmvvm.news.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

  @Query("SELECT * FROM news ORDER BY guid DESC")
  fun getNews(): LiveData<List<Item>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(plants: List<Item>)
}
