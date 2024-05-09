package org.d3if0152.bakuapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "books")
data class Books(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val penulis: String,
    val kategori: String,
    val totalHalaman: Int,
    val dibaca: Int
)
