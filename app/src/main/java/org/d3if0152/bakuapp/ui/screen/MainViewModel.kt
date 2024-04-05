import androidx.lifecycle.ViewModel
import org.d3if0152.bakuapp.model.Books

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    companion object {
        fun getDataDummy(): List<Books> {
            val data = mutableListOf<Books>()
            for (i in 29 downTo 20) {
                data.add(
                    Books(
                        i.toLong(),
                        "Bumi",
                        "Joe Davidson",
                        "Fiksi",
                        76,
                        54
                    )
                )
            }
            return data
        }
    }
}
