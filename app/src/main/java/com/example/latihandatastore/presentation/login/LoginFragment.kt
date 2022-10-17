package com.example.latihandatastore.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.latihandatastore.R
import com.example.latihandatastore.data.DataStoreManager
import com.example.latihandatastore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireContext())
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(dataStoreManager))[LoginViewModel::class.java]

        binding.tvDontHaveAccount.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener { toLoggingIn() }
    }

    private fun toLoggingIn() {
        val username = binding.etLoginUsername.text.toString()
        val password = binding.etLoginPassword.text.toString()

        var usernameAccount: String? = ""
        var passwordAccount: String? = ""

        loginViewModel.getUsername().observe(viewLifecycleOwner) {
            usernameAccount = it.toString()
        }

        loginViewModel.getPassword().observe(viewLifecycleOwner) {
            passwordAccount = it.toString()
        }

        if (username == usernameAccount && password == passwordAccount) {
            loginViewModel.statusLogin(true)
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Akun tidak sesuai", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.getLoginStatus().observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                requireContext()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}