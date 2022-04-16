package hilmysf.customnotesapp.helper

import androidx.recyclerview.widget.DiffUtil
import hilmysf.customnotesapp.data.source.entities.NoteEntity

class NoteDiffCallback(
    private val mOldNoteList: List<NoteEntity>,
    private val mNewNoteList: List<NoteEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mOldNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].noteId == mNewNoteList[newItemPosition].noteId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldNoteList[oldItemPosition]
        val newItem = mNewNoteList[newItemPosition]

        return oldItem.title == newItem.title && oldItem.content == oldItem.content
    }
}