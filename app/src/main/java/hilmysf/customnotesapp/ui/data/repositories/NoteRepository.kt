package hilmysf.customnotesapp.ui.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import hilmysf.customnotesapp.ui.data.source.LocalDataSource
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : NoteDataSource {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    override fun getAllNotes(): LiveData<PagedList<NoteEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getAllNotes(), config).build()
    }

    override fun getNoteById(noteId: Int?): NoteEntity? = localDataSource.getNoteById(noteId)

    override fun getLabelledNotes(isLabelled: Boolean): NoteEntity? =
        localDataSource.getLabelledNotes(isLabelled)

    override suspend fun insertNote(note: NoteEntity?) = localDataSource.insertNote(note)

    override suspend fun updateNote(note: NoteEntity?) = localDataSource.updateNote(note)

    override suspend fun deleteNote(note: NoteEntity?) = localDataSource.deleteNote(note)

}