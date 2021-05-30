package com.udacity.shoestore.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.MainActivity.Companion.EMAIL
import com.udacity.shoestore.MainActivity.Companion.LOGGEDIN
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.utils.SharedPreferencesManager


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.editTextTextEmailAddress.apply{
            addTextChangedListener(LoginValidationWatcher(this))
        }
        binding.editTextTextPassword.apply{
            addTextChangedListener(LoginValidationWatcher(this))
        }


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


    inner class LoginValidationWatcher(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(validateEmail() && validatePassword()){
                binding.newUserButton.isEnabled = true
                binding.loginButton.isEnabled = true
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.editTextTextEmailAddress -> {
                    validateEmail()
                }
                R.id.editTextTextPassword -> {
                    validatePassword()
                }
            }
        }
    }

    private fun validateEmail(): Boolean {
        if (binding.editTextTextEmailAddress.text.toString().trim().isEmpty()) {
            binding.editTextTextEmailAddress.error = getString(R.string.required_field)
            binding.editTextTextEmailAddress.requestFocus()
            return false
        } else if (!isValidEmail(binding.editTextTextEmailAddress.text.toString())) {
            binding.textInputLayoutEmailAddress.error = getString(R.string.invalid_email)
            binding.editTextTextEmailAddress.requestFocus()
            return false
        } else {
            binding.textInputLayoutEmailAddress.isErrorEnabled = false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(): Boolean {
        if (binding.editTextTextPassword.text.toString().trim().isEmpty()) {
            binding.editTextTextPassword.error = getString(R.string.required_field)
            binding.editTextTextPassword.requestFocus()
            return false
        } else if (binding.editTextTextPassword.text.toString().length < 6) {
            binding.textInputLayoutPassword.error = getString(R.string.invalid_password)
            binding.editTextTextPassword.requestFocus()
            return false
        } else {
            binding.textInputLayoutPassword.isErrorEnabled = false
        }
        return true
    }

}