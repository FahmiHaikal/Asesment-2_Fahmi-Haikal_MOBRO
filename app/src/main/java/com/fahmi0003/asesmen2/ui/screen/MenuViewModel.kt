package com.fahmi0003.asesmen2.ui.screen

import androidx.lifecycle.ViewModel
import com.fahmi0003.asesmen2.R
import com.fahmi0003.asesmen2.model.Catatan


    class MenuViewModel : ViewModel() {
        val data = getDataDummy()
        private fun getDataDummy(): List<Catatan> {
            val data = mutableListOf<Catatan>()
            data.add(Catatan(R.drawable.jusalpukat, "Jus Alpukat", "Rp.15.000,00", "Buah Segar!"))
            data.add(Catatan(R.drawable.jusanggur, "Jus Anggur", "Rp.15.000,00", "Buah Segar!"))
            data.add(Catatan(R.drawable.jusmangga, "Jus Mangga", "Rp.15.000,00", "Buah Segar!"))
            data.add(Catatan(R.drawable.jusjeruk, "Jus Jeruk", "Rp.15.000,00", "Buah Segar!"))
            data.add(Catatan(R.drawable.jussemangka, "Jus Semangka", "Rp.15.000,00", "Buah Segar!"))
            data.add(Catatan(R.drawable.kopitubruk, "Kopi Tubruk", "Rp.20.000,00", "Biji Kopi Segar"))
            data.add(Catatan(R.drawable.kopiluwak, "Kopi Luwak", "Rp.20.000,00", "Biji Kopi Segar"))
            data.add(Catatan(R.drawable.vsixten, "V60", "Rp.20.000,00", "Biji Kopi Segar"))
            data.add(Catatan(R.drawable.kopihitam, "Kopi Hitam", "Rp.20.000,00", "Biji Kopi Segar"))
            data.add(Catatan(R.drawable.kopisusu, "Kopi Susu", "Rp.20.000,00", "Biji Kopi Segar"))
            return data
        }
    }
