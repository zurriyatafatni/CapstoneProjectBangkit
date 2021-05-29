package com.djevannn.capstoneproject.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.djevannn.capstoneproject.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    // private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.appBarMain.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        binding.appBarMain.tvAppTitle.text = "Upload"
    }
}