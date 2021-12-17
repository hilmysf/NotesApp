package hilmysf.customnotesapp.ui.ui.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import hilmysf.customnotesapp.R
import hilmysf.customnotesapp.databinding.ActivityAddBinding
import hilmysf.customnotesapp.ui.data.source.entities.NoteEntity
import hilmysf.customnotesapp.ui.helper.DateHelper
import hilmysf.customnotesapp.ui.ui.MainActivity

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    companion object {
        var EXTRA_NOTE = "extra_note"
        val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private lateinit var activityAddBinding: ActivityAddBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private var isEdit = false
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
//        var timestamp = activityAddBinding.tvTimestamp.text

        val intentParcelable = intent.getParcelableExtra<NoteEntity>(EXTRA_NOTE)
        if (intentParcelable == null) {

        } else {
            activityAddBinding.edtTitle.setText(intentParcelable.title.toString())
            activityAddBinding.edtContent.setText(intentParcelable.content.toString())
            activityAddBinding.tvTimestamp.text = "Edited ${intentParcelable.date}"
        }
//        timestamp = DateHelper.getCurrentDate()
//        note.date = timestamp



        activityAddBinding.ibBackBtn.setOnClickListener {
            val intent = Intent(this@AddActivity, MainActivity::class.java)
            startActivity(intent)
        }

        activityAddBinding.ibLabelBtn.setOnClickListener {
            if (intentParcelable == null) {
                insertNote()
            } else {
                updateNote()
            }
        }
        showBottomSheetDialog()
//        storeTextData()
    }

    private fun insertNote(){
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
                viewModel.insertNote(it)
            }
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AddActivity, MainActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
                putExtra(EXTRA_POSITION, position)
            }
            startActivity(intent)
        }
    }

    private fun updateNote() {
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
                viewModel.updateNote(it)
            }
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AddActivity, MainActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
                putExtra(EXTRA_POSITION, position)
            }
            startActivity(intent)
        }
    }

    private fun showBottomSheetDialog() {
        activityAddBinding.btnMore.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog(this@AddActivity, R.style.BottomSheetTheme)
            val sheetView = LayoutInflater.from(applicationContext)
                .inflate(R.layout.bottom_sheet_layout, findViewById(R.id.bottom_sheet))

            sheetView.findViewById<View>(R.id.group_delete).setOnClickListener {
                Toast.makeText(applicationContext, "Note Deleted", Toast.LENGTH_SHORT).show()
                bottomSheetDialog.dismiss()
                note.let {
                    viewModel.deleteNote(it)
                }
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
            bottomSheetDialog.setContentView(sheetView)
            bottomSheetDialog.show()
        }
    }
//
//    private fun storeTextData(){
//        activityAddBinding.edtTitle.addTextChangedListener(object : TextWatcher {
//
//            var timer: CountDownTimer? = null
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                timer?.cancel()
//                timer = object : CountDownTimer(1000, 1500) {
//                    override fun onTick(millisUntilFinished: Long) {}
//                    override fun onFinish() {
//                        //Put your data storing code here
//                        val title = activityAddBinding.edtTitle.text.toString()
//                        val content = activityAddBinding.edtContent.text.toString()
//                        if (title.isEmpty()){
//                            activityAddBinding.edtTitle.error = "insert title"
//                        }
//                        else if (content.isEmpty()){
//                            activityAddBinding.edtContent.error = "insert content"
//                        }else{
//                            note.let {
//                                it?.title = title
//                                it?.content = content
//                            }
//                            viewModel.insertNote(note)
////                            val intent = Intent(this@AddActivity, MainActivity::class.java).apply {
////                                putExtra(EXTRA_NOTE, note)
////                                putExtra(EXTRA_POSITION, position)
////                            }
////                            startActivity(intent)
//                        }
//
//                    }
//                }.start()
//            }
//        })
//    }
}