import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if0152.bakuapp.model.Books
import org.d3if0152.bakuapp.network.ApiStatus
import org.d3if0152.bakuapp.network.BooksApi
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel(){
    var data = mutableStateOf(emptyList<Books>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun retrieveData(IdPengguna: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = BooksApi.service.getBooks(IdPengguna)
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun saveData(IdPengguna: String, judul: String, kategori : String, totalhalaman: Int, bitmap: Bitmap) {
        val totalHalamanBody = totalhalaman.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = BooksApi.service.postBooks(
                    IdPengguna,
                    judul.toRequestBody("text/plain".toMediaTypeOrNull()),
                    kategori.toRequestBody("text/plain".toMediaTypeOrNull()),
                    totalHalamanBody,
                    bitmap.toMultipartBody()
                )

                if (result.status == "success")
                    retrieveData(IdPengguna)
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun deleteData(IdPengguna: String, booksId: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = BooksApi.service.deleteBooks(IdPengguna, booksId)
                if (response.status == "success") {
                    Log.d("MainViewModel", "Book deleted: $booksId")
                    retrieveData(IdPengguna) // Refresh data after deletion
                } else {
                    Log.d("MainViewModel", "Failed to delete the book: ${response.message}")
                    errorMessage.value = "Failure: ${response.message}"
                }
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(), 0, byteArray.size)
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody)
    }

    fun clearMessage() { errorMessage.value = null }

}