package org.d3if0152.bakuapp.model

data class Books(
    val id: Long,
    val judul: String,
    val penulis: String,
    val kategori: String,
    val totalHalaman: Int,
    val dibaca: Int
)
