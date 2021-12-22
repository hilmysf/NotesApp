package hilmysf.customnotesapp.ui.ui.home

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
import hilmysf.customnotesapp.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewConfiguration(fragmentHomeBinding)
        noteAdapter = NoteAdapter(requireActivity())
        getNotesData()
    }

    private fun searchViewConfiguration(fragmentHomeBinding: FragmentHomeBinding) {
        val searchView = fragmentHomeBinding.searchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this);
        searchView.isSubmitButtonEnabled = false
        searchView.background
    }

    private fun getNotesData() {
        viewModel.getAllNotes().observe(viewLifecycleOwner, {
            noteAdapter.submitList(it)
        })
        with(fragmentHomeBinding.rvNotes) {
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