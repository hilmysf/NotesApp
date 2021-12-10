package hilmysf.customnotesapp.ui.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}