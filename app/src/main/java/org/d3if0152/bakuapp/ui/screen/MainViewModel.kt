import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if0152.bakuapp.database.BooksDao
import org.d3if0152.bakuapp.model.Books

class MainViewModel (dao: BooksDao) : ViewModel() {
    val data: StateFlow<List<Books>> = dao.getBuku().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}
