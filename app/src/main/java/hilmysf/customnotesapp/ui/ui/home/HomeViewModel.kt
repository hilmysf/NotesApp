package hilmysf.customnotesapp.ui.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import hilmysf.customnotesapp.ui.data.repositories.NoteRepository
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    fun getAllNotes(): LiveData<PagedList<NoteEntity>> = noteRepository.getAllNotes()

    fun searchQuery(searchQuery: String): LiveData<PagedList<NoteEntity>> = noteRepository.searchDatabase(searchQuery)

}