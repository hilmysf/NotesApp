package hilmysf.customnotesapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import hilmysf.customnotesapp.data.source.entities.NoteEntity

interface NoteDataSource {
    fun getAllNotes(): LiveData<PagedList<NoteEntity>>

    fun getNoteById(noteId: Int?): NoteEntity?

    fun getLabeledNotes(isLabelled: Boolean): LiveData<PagedList<NoteEntity>>

    suspend fun insertNote(note: NoteEntity?)

    suspend fun updateNote(note: NoteEntity?)

    suspend fun deleteNote(note: NoteEntity?)

    fun searchDatabase(searchQuery: String): LiveData<PagedList<NoteEntity>>

//    suspend fun insertLabeledNote(note: NoteEntity?)
}