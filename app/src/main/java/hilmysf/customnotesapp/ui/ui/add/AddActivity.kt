package hilmysf.customnotesapp.ui.ui.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import hilmysf.customnotesapp.R
import hilmysf.customnotesapp.databinding.ActivityAddBinding
import hilmysf.customnotesapp.databinding.BottomSheetLayoutBinding
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import hilmysf.customnotesapp.ui.helper.DateHelper
import hilmysf.customnotesapp.ui.ui.MainActivity

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    companion object {
        var EXTRA_NOTE = "extra_note"
        val EXTRA_POSITION = "extra_position"
        const val REQUEST_UPDATE = 200
    }

    private lateinit var activityAddBinding: ActivityAddBinding
    private lateinit var bottomSheetLayoutBinding: BottomSheetLayoutBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private var note: NoteEntity = NoteEntity()
    private var position = 0
    private val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)
        setSupportActionBar(activityAddBinding.myToolbar)
        supportActionBar?.title = null
        supportActionBar?.elevation = 0f

        activityAddBinding.tvTimestamp.text = "Edited ${DateHelper.getCurrentDate()}"
        val intentParcelable = intent.getParcelableExtra<NoteEntity>(EXTRA_NOTE)
        if (intentParcelable != null) {
            activityAddBinding.edtTitle.setText(intentParcelable.title.toString())
            activityAddBinding.edtContent.setText(intentParcelable.content.toString())
            activityAddBinding.tvTimestamp.text = "Edited ${intentParcelable.date}"
            note.label = intentParcelable.label
            note.color = intentParcelable.color
        }
        onClick()
        showBottomSheetDialog(intentParcelable)
    }

    override fun onStop() {
        super.onStop()
        val intentParcelable = intent.getParcelableExtra<NoteEntity>(EXTRA_NOTE)
        insertUpdateNote(intentParcelable)
        Log.i("event", "onStop")
    }

    private fun onClick() {
        activityAddBinding.ibBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun insertUpdateNote(intentParcelable: NoteEntity?) {
        val title = activityAddBinding.edtTitle.text.toString()
        val content = activityAddBinding.edtContent.text.toString()
        if (title.isEmpty()) {
            activityAddBinding.edtTitle.error = "insert title"
        } else if (content.isEmpty()) {
            activityAddBinding.edtContent.error = "insert content"
        } else {
            note.let {
                it.title = title
                it.content = content
                it.date = DateHelper.getCurrentDate()
                if (intentParcelable == null) {
                    viewModel.insertNote(it)
                } else {
                    it.noteId = intentParcelable.noteId
                    it.label = intentParcelable.label
                    viewModel.updateNote(it)
                }
            }
            if (intentParcelable == null) Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT)
                .show() else Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseBackgroundColor(intentParcelable: NoteEntity?, color: Int) {
        note.let {
            it.color = ContextCompat.getColor(applicationContext, color)
            if (intentParcelable == null) {
                viewModel.insertNote(it)
            } else {
                it.noteId = intentParcelable.noteId
                viewModel.updateNote(it)
            }
            bottomSheetDialog.dismiss()
        }
    }

    private fun showBottomSheetDialog(intentParcelable: NoteEntity?) {
        activityAddBinding.btnMore.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog(this@AddActivity, R.style.BottomSheetTheme)
            val sheetView = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottom_sheet_layout, findViewById(R.id.bottom_sheet))

            sheetView.findViewById<View>(R.id.imageFilterButton1).setOnClickListener {
                chooseBackgroundColor(intentParcelable, R.color.ThemeOne)
            }
            sheetView.findViewById<View>(R.id.imageFilterButton2).setOnClickListener {
                chooseBackgroundColor(intentParcelable, R.color.ThemeTwo)
            }
            sheetView.findViewById<View>(R.id.imageFilterButton3).setOnClickListener {
                chooseBackgroundColor(intentParcelable, R.color.ThemeThree)
            }
            sheetView.findViewById<View>(R.id.imageFilterButton4).setOnClickListener {
                chooseBackgroundColor(intentParcelable, R.color.ThemeFour)
            }
            sheetView.findViewById<View>(R.id.imageFilterButton5).setOnClickListener {
                chooseBackgroundColor(intentParcelable, R.color.ThemeFive)
            }
            sheetView.findViewById<View>(R.id.imageFilterButton6).setOnClickListener {
                chooseBackgroundColor(intentParcelable, R.color.ThemeSix)
            }
            sheetView.findViewById<View>(R.id.group_delete).setOnClickListener {
                Toast.makeText(applicationContext, "Note Deleted", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
                viewModel.deleteNote(intentParcelable)
                startActivity(Intent(this@AddActivity, MainActivity::class.java))
            }
            sheetView.findViewById<View>(R.id.group_copy).setOnClickListener {
                Toast.makeText(applicationContext, "Note Copied", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            }
            sheetView.findViewById<View>(R.id.group_share).setOnClickListener {
                Toast.makeText(applicationContext, "Note Shared", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            }
            sheetView.findViewById<View>(R.id.group_label).setOnClickListener {
                intentParcelable.let {
                    it?.label = true
                    viewModel.updateNote(it)
                }
                Toast.makeText(applicationContext, "Note Labeled", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(sheetView)
            bottomSheetDialog.show()
        }
    }
}