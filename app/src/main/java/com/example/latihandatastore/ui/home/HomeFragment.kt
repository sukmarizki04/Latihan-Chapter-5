package com.example.latihandatastore.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.latihandatastore.data.DataStoreManager
import com.example.latihandatastore.databinding.FragmentHomeBinding
import com.example.latihandatastore.presentation.login.LoginViewModel
import com.example.latihandatastore.presentation.login.LoginViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(dataStoreManager))[LoginViewModel::class.java]

        loginViewModel.getUsername().observe(viewLifecycleOwner) {
            binding.tvUsername.text = "Halo " + it.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}