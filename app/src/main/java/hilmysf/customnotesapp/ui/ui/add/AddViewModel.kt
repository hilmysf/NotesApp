package hilmysf.customnotesapp.ui.ui.add

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hilmysf.customnotesapp.ui.data.repositories.NoteRepository
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel @ViewModelInject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    fun insertNote(note: NoteEntity?) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: NoteEntity?) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: NoteEntity?) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteNote(note)
    }
}