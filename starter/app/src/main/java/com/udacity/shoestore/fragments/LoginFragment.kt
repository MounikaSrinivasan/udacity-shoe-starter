package com.udacity.shoestore.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.MainActivity.Companion.EMAIL
import com.udacity.shoestore.MainActivity.Companion.LOGGEDIN
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.utils.SharedPreferencesManager


class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)


        binding.loginButton.setOnClickListener {
            SharedPreferencesManager.write(EMAIL, binding.editTextTextEmailAddress.toString())
            SharedPreferencesManager.write(LOGGEDIN, true)
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        binding.newUserButton.setOnClickListener {
            SharedPreferencesManager.write(EMAIL, binding.editTextTextEmailAddress.toString())
            SharedPreferencesManager.write(LOGGEDIN, true)
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        return binding.root
    }

}