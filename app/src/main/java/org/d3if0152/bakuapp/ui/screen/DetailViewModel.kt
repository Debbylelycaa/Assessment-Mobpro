package org.d3if0152.bakuapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0152.bakuapp.database.BooksDao
import org.d3if0152.bakuapp.model.Books

class DetailViewModel (private val dao : BooksDao) : ViewModel() {
    fun insert(judul: String, penulis: String, kategori: String, totalHalaman: Int, dibaca: Int){
        val books = Books(
            judul = judul,
            penulis = penulis,
            kategori = kategori,
            totalHalaman = totalHalaman,
            dibaca = dibaca
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(books)
        }
    }
    suspend fun getBuku(id: Long) : Books? {
        return dao.getBukuById(id)
    }

    fun update(id: Long, judul: String, penulis: String, kategori: String, totalHalaman: Int, dibaca: Int){
        val books = Books(
            id = id, // Set the id field
            judul = judul,
            penulis = penulis,
            kategori = kategori,
            totalHalaman = totalHalaman,
            dibaca = dibaca
        )

        viewModelScope.launch(Dispatchers.IO){
            dao.update(books)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            dao.deleteById(id)
        }
    }
}



