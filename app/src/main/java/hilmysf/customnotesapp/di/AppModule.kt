package hilmysf.customnotesapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hilmysf.customnotesapp.data.source.LocalDataSource
import hilmysf.customnotesapp.data.source.room.NoteDao
import hilmysf.customnotesapp.data.source.room.NoteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(mNoteDao: NoteDao): LocalDataSource =
        LocalDataSource(mNoteDao)

    @Singleton
    @Provides
    fun provideDao(db: NoteDatabase) = db.noteDao()

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        "note.db"
    ).build()

}