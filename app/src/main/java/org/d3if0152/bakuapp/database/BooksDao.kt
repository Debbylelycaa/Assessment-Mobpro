package org.d3if0152.bakuapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0152.bakuapp.model.Books

@Dao
interface BooksDao {
    @Insert
    suspend fun insert(books: Books)

    @Update
    suspend fun update(books: Books)

    @Query("SELECT * FROM books ORDER BY judul ASC")
    fun getBuku(): Flow<List<Books>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBukuById(id: Long): Books?

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteById(id: Long)
}