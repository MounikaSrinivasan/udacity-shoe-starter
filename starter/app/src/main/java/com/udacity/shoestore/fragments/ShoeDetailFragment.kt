package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utils.ShoeActionHandler
import com.udacity.shoestore.viewmodels.MainVM
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment(), ShoeActionHandler {

    private lateinit var shoeVM: ShoeViewModel
    private lateinit var binding : FragmentShoeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoeVM = ViewModelProvider(this.requireActivity()).get(ShoeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.shoeVM = shoeVM
        binding.shoeHandler = this
        binding.lifecycleOwner = this

        arguments?.let {
            val index = ShoeDetailFragmentArgs.fromBundle(it).shoeId
            if(index!=-1) {
                binding.editTextShoeName.setText(shoeVM.shoesList.value?.get(index)?.name)
                binding.editTextBrandName.setText(shoeVM.shoesList.value?.get(index)?.company)
                shoeVM.shoesList.value?.get(index)?.size?.toString()?.let { it1 ->
                    binding.editTextShoeSize.setText(
                        it1
                    )
                }
                binding.editTextDescription.setText(shoeVM.shoesList.value?.get(index)?.description)
            }
        }

        binding.buttonCancelAction.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_shoeDetailFragment_to_shoesListFragment)
        }

        binding.buttonSubmitAction.setOnClickListener {

            shoeVM.shoesList.value?.add(Shoe(binding.editTextShoeName.text.toString(),
                binding.editTextShoeSize.text.toString().toDouble(),
                binding.editTextBrandName.text.toString(),
                binding.editTextDescription.text.toString()))

        }
        return binding.root
    }

    override fun submitClicked(view:View) {
        shoeVM.submitClicked(view,Shoe(binding.editTextShoeName.text.toString(),
            binding.editTextShoeSize.text.toString().toDouble(),
            binding.editTextBrandName.text.toString(),
        binding.editTextDescription.text.toString()))
    }

    override fun cancelClicked(view: View) {
        shoeVM.cancelClicked(view)
    }

}