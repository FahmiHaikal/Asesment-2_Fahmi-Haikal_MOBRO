package com.fahmi0003.asesmen2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import com.fahmi0003.asesmen2.database.MinumanDao
import com.fahmi0003.asesmen2.model.Minuman

class DataViewModel(dao: MinumanDao) : ViewModel() {

    val data: StateFlow<List<Minuman>> = dao.getMinuman().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}