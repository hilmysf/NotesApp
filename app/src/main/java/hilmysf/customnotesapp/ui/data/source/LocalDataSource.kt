package hilmysf.customnotesapp.ui.data.source

import androidx.paging.DataSource
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import hilmysf.customnotesapp.ui.data.source.room.NoteDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mNoteDao: NoteDao){

    fun getAllNotes(): DataSource.Factory<Int, NoteEntity> = mNoteDao.getAllNotes()

    fun getNoteById(noteId: Int?): NoteEntity? = mNoteDao.getNoteById(noteId)

    fun getLabelledNotes(isLabelled: Boolean): NoteEntity? = mNoteDao.getLabelledNotes(isLabelled)

    suspend fun insertNote(note: NoteEntity?) = mNoteDao.insertNote(note)

    suspend fun updateNote(note: NoteEntity?) = mNoteDao.updateNote(note)

    suspend fun deleteNote(note: NoteEntity?) = mNoteDao.deleteNote(note)

}