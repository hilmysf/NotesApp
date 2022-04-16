package hilmysf.customnotesapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hilmysf.customnotesapp.data.repositories.NoteRepository
import hilmysf.customnotesapp.data.source.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val noteRepository: NoteRepository) :
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