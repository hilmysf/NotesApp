package hilmysf.customnotesapp.ui.data.source.room

import androidx.paging.DataSource
import androidx.room.*
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteentity")
    fun getAllNotes(): DataSource.Factory<Int, NoteEntity>

    @Query("SELECT * FROM noteentity WHERE noteId = :noteId")
    fun getNoteById(noteId: Int?): NoteEntity?

    @Query("SELECT * FROM noteentity WHERE label = :isLabeled")
    fun getLabeledNotes(isLabeled: Boolean): DataSource.Factory<Int, NoteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: NoteEntity?)

    @Update
    fun updateNote(note: NoteEntity?)

    @Delete
    fun deleteNote(note: NoteEntity?)

    @Query("SELECT * FROM noteentity WHERE title LIKE :searchQuery OR content LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): DataSource.Factory<Int, NoteEntity>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertLabeledNote(note: NoteEntity?)
}