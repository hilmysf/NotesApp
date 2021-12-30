package hilmysf.customnotesapp.ui.ui.label

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hilmysf.customnotesapp.databinding.FragmentLabelBinding
import hilmysf.customnotesapp.ui.ui.home.NoteAdapter

@AndroidEntryPoint
class LabelFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var fragmentLabelBinding: FragmentLabelBinding
    private val viewModel: LabelViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLabelBinding = FragmentLabelBinding.inflate(layoutInflater, container, false)
        return fragmentLabelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewConfiguration(fragmentLabelBinding)
        noteAdapter = NoteAdapter(requireActivity())
        getLabeledNotes(isLabelled = true)
    }

    private fun searchViewConfiguration(fragmentLabelBinding: FragmentLabelBinding) {
        val searchView = fragmentLabelBinding.searchView
//        if(searchView.isActivated) searchView.canc
        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(this);
        searchView.isSubmitButtonEnabled = false

        searchView.background
    }

    private fun getLabeledNotes(isLabelled: Boolean) {
        viewModel.getLabeledNotes(isLabelled).observe(viewLifecycleOwner, {
            noteAdapter.submitList(it)
        })
        with(fragmentLabelBinding.rvLabels) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = noteAdapter
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null)
            searchDatabase(query)
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchQuery(searchQuery)
            .observe(viewLifecycleOwner, {
                noteAdapter.submitList(it)
            })
    }
}