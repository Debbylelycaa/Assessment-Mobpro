package org.d3if0152.bakuapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if0152.bakuapp.model.Books
import org.d3if0152.bakuapp.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


private const val BASE_URL = "https://unspoken.my.id/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    @GET("debby.php")
    suspend fun getBooks(
        @Header("Authorization") IdPengguna : String
    ): List<Books>

    @Multipart
    @POST("debby.php")
    suspend fun postBooks(
        @Header("Authorization") IdPengguna: String,
        @Part("judulBuku") judulBuku: RequestBody,
        @Part("kategori") kategori: RequestBody,
        @Part("totalHalaman") totalhalaman: RequestBody,
        @Part image: MultipartBody.Part
            ): OpStatus
    @DELETE("debby.php")
    suspend fun deleteBooks(
        @Header("Authorization") IdPengguna: String,
        @Query("id") booksId: String
    ) : OpStatus
}

object BooksApi {
    val service: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    fun getBooksUrl(imageId: String): String {
       return "${BASE_URL}image.php?id=$imageId"
    }
}
enum class ApiStatus {LOADING, SUCCESS, FAILED}
