package hilmysf.customnotesapp.ui.ui.label

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import hilmysf.customnotesapp.ui.data.repositories.NoteRepository
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LabelViewModel @ViewModelInject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    fun getAllNotes(): LiveData<PagedList<NoteEntity>> = noteRepository.getAllNotes()

    fun getLabeledNotes(isLabelled: Boolean): LiveData<PagedList<NoteEntity>> = noteRepository.getLabeledNotes(isLabelled)

    fun searchQuery(searchQuery: String): LiveData<PagedList<NoteEntity>> =
        noteRepository.searchDatabase(searchQuery)

//    fun insertLabeledNote(note: NoteEntity?) =
//        viewModelScope.launch(Dispatchers.IO) { noteRepository.insertLabeledNote(note) }
}