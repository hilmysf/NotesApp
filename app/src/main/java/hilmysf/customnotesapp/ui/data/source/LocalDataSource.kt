package hilmysf.customnotesapp.ui.data.source

import androidx.paging.DataSource
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import hilmysf.customnotesapp.ui.data.source.room.NoteDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mNoteDao: NoteDao) {

    fun getAllNotes(): DataSource.Factory<Int, NoteEntity> = mNoteDao.getAllNotes()

    fun getNoteById(noteId: Int?): NoteEntity? = mNoteDao.getNoteById(noteId)

    fun getLabeledNotes(isLabelled: Boolean): DataSource.Factory<Int, NoteEntity> = mNoteDao.getLabeledNotes(isLabelled)

    fun insertNote(note: NoteEntity?) = mNoteDao.insertNote(note)

    fun updateNote(note: NoteEntity?) = mNoteDao.updateNote(note)

    fun deleteNote(note: NoteEntity?) = mNoteDao.deleteNote(note)

    fun searchDatabase(searchQuery: String): DataSource.Factory<Int, NoteEntity> =
        mNoteDao.searchDatabase(searchQuery)

//    fun insertLabeledNote(note: NoteEntity?) = mNoteDao.insertLabeledNote(note)
}