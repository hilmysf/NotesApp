package hilmysf.customnotesapp.ui.label

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import hilmysf.customnotesapp.data.repositories.NoteRepository
import hilmysf.customnotesapp.data.source.entities.NoteEntity
import javax.inject.Inject

@HiltViewModel
class LabelViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    fun getLabeledNotes(isLabelled: Boolean): LiveData<PagedList<NoteEntity>> =
        noteRepository.getLabeledNotes(isLabelled)

    fun searchQuery(searchQuery: String): LiveData<PagedList<NoteEntity>> =
        noteRepository.searchDatabase(searchQuery)

}