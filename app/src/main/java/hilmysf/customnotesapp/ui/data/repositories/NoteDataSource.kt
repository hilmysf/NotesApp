package hilmysf.customnotesapp.ui.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity

interface NoteDataSource {
    fun getAllNotes(): LiveData<PagedList<NoteEntity>>

    fun getNoteById(noteId: Int?): NoteEntity?

    fun getLabelledNotes(isLabelled: Boolean): NoteEntity?

    suspend fun insertNote(note: NoteEntity?)

    suspend fun updateNote(note: NoteEntity?)

    suspend fun deleteNote(note: NoteEntity?)

}