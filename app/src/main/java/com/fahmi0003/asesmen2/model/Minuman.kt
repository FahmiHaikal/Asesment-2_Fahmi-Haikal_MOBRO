package com.fahmi0003.asesmen2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minuman")
data class Minuman(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val harga: String,
    val jenis: String,
    var selectedOption:String=""

)