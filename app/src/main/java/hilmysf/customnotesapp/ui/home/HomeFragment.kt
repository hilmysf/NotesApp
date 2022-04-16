package hilmysf.customnotesapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hilmysf.customnotesapp.R
import hilmysf.customnotesapp.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter
    private var isList = false
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
        fragmentHomeBinding.ibChangeView.setOnClickListener {
            isList = !isList
            changeLayoutView(isList, context)
            getNotesData()
        }
        getNotesData()
    }

    private fun changeLayoutView(isList: Boolean, context: Context?) {
        fragmentHomeBinding.ibChangeView.setImageDrawable(
            context?.let {
                ContextCompat.getDrawable(
                    it,
                    if (isList) R.drawable.list_view else R.drawable.grid_view
                )
            }
        )
    }

    private fun searchViewConfiguration(fragmentHomeBinding: FragmentHomeBinding) {
        val searchView = fragmentHomeBinding.searchView
        searchView.setOnQueryTextListener(this)
        searchView.isSubmitButtonEnabled = false
    }

    private fun getNotesData() {
        viewModel.getAllNotes().observe(viewLifecycleOwner, {
            noteAdapter.submitList(it)
        })
        with(fragmentHomeBinding.rvNotes) {
            layoutManager =
                if (isList) LinearLayoutManager(context) else StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
            Log.i("isList", "$isList")
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