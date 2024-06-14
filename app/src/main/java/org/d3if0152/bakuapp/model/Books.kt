package org.d3if0152.bakuapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "books")
data class Books(
    @PrimaryKey val id: String,
    val judulBuku: String,
    val userId: String,
    val kategori: String,
    val totalHalaman: Int,
    val imageId: String,
//    val mine: Int
)
