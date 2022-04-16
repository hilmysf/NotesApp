package hilmysf.customnotesapp.data.source.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "noteentity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    var noteId: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "content")
    var content: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "label")
    var label: Boolean = false,

    @ColumnInfo(name = "color")
    var color: Int = 0,

): Parcelable
