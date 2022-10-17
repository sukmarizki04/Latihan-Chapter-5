package com.example.latihandatastore.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.latihandatastore.R
import com.example.latihandatastore.data.DataStoreManager
import com.example.latihandatastore.databinding.FragmentRegisterBinding
import com.example.latihandatastore.presentation.login.LoginViewModelFactory

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerViewModel: RegisterViewModel

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())
        registerViewModel = ViewModelProvider(this, LoginViewModelFactory(dataStoreManager))[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener { toCreateAccount() }
    }

    private fun toCreateAccount() {
        val username = binding.etRegisterUsername.text.toString()
        val password = binding.etRegisterPassword.text.toString()
        val confirmPassword = binding.etRegisterConfirmPassword.text.toString()

        if (password == confirmPassword) {
            registerViewModel.saveAccount(username, password)
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        } else {
            Toast.makeText(requireContext(), "Password tidak sesuai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}