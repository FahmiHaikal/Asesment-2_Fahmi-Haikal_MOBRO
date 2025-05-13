package com.fahmi0003.asesmen2.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahmi0003.asesmen2.database.MinumanDao
import com.fahmi0003.asesmen2.ui.screen.DataViewModel
import com.fahmi0003.asesmen2.ui.screen.DetailViewModel

class ViewModelFactory {
    class ViewModelFactory(private val dao: MinumanDao) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
                return DataViewModel(dao) as T
            } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}