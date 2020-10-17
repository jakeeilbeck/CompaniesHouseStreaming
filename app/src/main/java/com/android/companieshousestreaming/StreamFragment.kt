package com.android.companieshousestreaming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.android.companieshousestreaming.databinding.FragmentStreamBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StreamFragment: Fragment(R.layout.fragment_stream) {
    private var binding: FragmentStreamBinding? = null
    private var getJob: Job? = null
    private lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(ViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentStreamBinding.bind(view)

        adapter = RecyclerViewAdapter(requireContext())
        recyclerView = binding.recyclerView



        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.getStream().collectLatest {
                adapter.submitData(it)
            }
        }

    }
}