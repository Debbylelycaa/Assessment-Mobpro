package org.d3if0152.bakuapp.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0152.bakuapp.model.Books

class EditlViewModel : ViewModel() {

    fun getBuku(id: Long) : Books {
        return Books(
            id,
            "Bumi",
            "Tere",
            "Fiction",
            800,
            90

        )
    }
}

