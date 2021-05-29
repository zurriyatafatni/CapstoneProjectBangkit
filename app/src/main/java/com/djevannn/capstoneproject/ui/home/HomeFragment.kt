package com.djevannn.capstoneproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.djevannn.capstoneproject.data.Resource
import com.djevannn.capstoneproject.data.source.local.entity.BookEntity
import com.djevannn.capstoneproject.databinding.FragmentHomeBinding
import com.djevannn.capstoneproject.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()
        if (activity != null) {
            val homeAdapter = HomeAdapter()
            homeAdapter.onItemClick = { selectedData ->
                 (activity as MainActivity).setMusic(selectedData)
            }
            homeViewModel.books
                .observe(viewLifecycleOwner, { books ->
                    if (books != null) {
                        when (books) {
                            is Resource.Loading -> binding.progressBar.visibility =
                                View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility =
                                    View.GONE
                                homeAdapter.setData(books.data)
                                (activity as MainActivity).initMusic(
                                    books.data?.get(0)
                                )
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility =
                                    View.GONE
                                // error goes here
                            }
                        }
                    }
                })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = homeAdapter
            }
        }
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.appBarMain.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        binding.appBarMain.tvAppTitle.text = "Home"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}