package hilmysf.customnotesapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import hilmysf.customnotesapp.data.source.LocalDataSource
import hilmysf.customnotesapp.data.source.entities.NoteEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : NoteDataSource {

    override fun getAllNotes(): LiveData<PagedList<NoteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getAllNotes(), config).build()
    }

    override fun getNoteById(noteId: Int?): NoteEntity? = localDataSource.getNoteById(noteId)

    override fun getLabeledNotes(isLabelled: Boolean): LiveData<PagedList<NoteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getLabeledNotes(isLabelled), config).build()
    }

    override suspend fun insertNote(note: NoteEntity?) = localDataSource.insertNote(note)

    override suspend fun updateNote(note: NoteEntity?) = localDataSource.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity?) = localDataSource.deleteNote(note)

    override fun searchDatabase(searchQuery: String): LiveData<PagedList<NoteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.searchDatabase(searchQuery), config).build()
    }
}