package hilmysf.customnotesapp.ui.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hilmysf.customnotesapp.databinding.ItemNoteBinding
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import hilmysf.customnotesapp.ui.ui.add.AddActivity

class NoteAdapter(private val activity: Activity) :
    PagedListAdapter<NoteEntity, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NoteEntity>() {
            override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem.noteId == newItem.noteId
            }

            override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            with(binding) {
                itemTitle.text = note.title
                itemContent.text = note.content
                itemView.setOnClickListener {

                    val intent = Intent(itemView.context, AddActivity::class.java).apply {
                        putExtra(AddActivity.EXTRA_NOTE, note)
                        putExtra(AddActivity.EXTRA_POSITION, adapterPosition)
                    }
                    activity.startActivityForResult(intent, AddActivity.REQUEST_UPDATE)

                }
            }
        }

    }

//    private val noteList = ArrayList<NoteEntity>()

//    fun setNotesList(noteList: List<NoteEntity>) {
//        val diffCallback = NoteDiffCallback(this.noteList, noteList)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        this.noteList.clear()
//        this.noteList.addAll(noteList)
//        diffResult.dispatchUpdatesTo(this)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        if (note != null) {
            holder.bind(note)
        }
    }

    fun getSwipeData(swipedPosition: Int): NoteEntity? = getItem(swipedPosition)
}