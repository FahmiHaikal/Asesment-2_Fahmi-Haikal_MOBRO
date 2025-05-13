package com.fahmi0003.asesmen2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.fahmi0003.asesmen2.model.Minuman

@Dao
interface MinumanDao {
    @Insert
    suspend fun insert(minuman: Minuman)

    @Update
    suspend fun update(minuman: Minuman)

    @Query("SELECT * FROM minuman ORDER BY jenis ASC")
    fun getMinuman(): Flow<List<Minuman>>

    @Query("SELECT * FROM minuman WHERE id = :id")
    suspend fun getMinumanById(id: Long): Minuman?
    @Query("DELETE FROM minuman WHERE id = :id")
    suspend fun deleteById(id:Long)

}