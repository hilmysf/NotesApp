package hilmysf.customnotesapp.ui.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.PagedList
import hilmysf.customnotesapp.ui.data.repositories.NoteRepository
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity

class HomeViewModel @ViewModelInject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    fun getAllNotes(): LiveData<PagedList<NoteEntity>> = noteRepository.getAllNotes()

    fun searchQuery(searchQuery: String): LiveData<PagedList<NoteEntity>> = noteRepository.searchDatabase(searchQuery)

}