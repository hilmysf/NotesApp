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

    @Query("SELECT * FROM noteentity WHERE label = :isLabelled")
    fun getLabelledNotes(isLabelled: Boolean): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: NoteEntity?)

    @Update
    suspend fun updateNote(note: NoteEntity?)

    @Delete
    suspend fun deleteNote(note: NoteEntity?)
}