package com.fahmi0003.asesmen2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.fahmi0003.asesmen2.database.MinumanDao
import com.fahmi0003.asesmen2.model.Minuman

class DetailViewModel(private val dao: MinumanDao) : ViewModel() {

    fun insert(nama: String, harga: String, jenis: String) {
        val minuman = Minuman(
            nama = nama,
            harga = harga,
            jenis = jenis,
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(minuman)
        }
    }
    suspend fun getMinuman(id: Long): Minuman? {
        return dao.getMinumanById(id)
    }
    fun update(id: Long, nama: String, harga: String, jenis: String) {
        val minuman = Minuman(
            id = id,
            nama = nama,
            harga = harga,
            jenis = jenis,
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(minuman)
        }
    }
    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}