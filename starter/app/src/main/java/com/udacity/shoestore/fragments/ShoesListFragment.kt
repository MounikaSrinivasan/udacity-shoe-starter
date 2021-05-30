package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListItemBinding
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.viewmodels.ShoeViewModel


class ShoesListFragment : Fragment() {


    private val shoeVM: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentShoesListBinding>(
            inflater,
            R.layout.fragment_shoes_list,
            container,
            false
        )


        shoeVM.shoesList.value?.forEachIndexed { index, shoe ->
            val item = DataBindingUtil.inflate<FragmentShoeListItemBinding>(
                inflater,
                R.layout.fragment_shoe_list_item,
                binding.shoeslist,
                false
            )

            item.textShoeName.text = shoe.name
            item.textBrandName.text = shoe.company

            item.shoeDetails.setOnClickListener {
                view?.findNavController()
                    ?.navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(index))
            }

            binding.shoeslist.addView(item.root)
        }

        binding.fabButton.setOnClickListener {
            shoeVM.clearShoeData()
            view?.findNavController()?.navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(-1))
        }
        return binding.root
    }

}