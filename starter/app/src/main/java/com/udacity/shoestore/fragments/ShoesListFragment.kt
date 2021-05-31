package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListItemBinding
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.utils.SharedPreferencesManager
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
        setHasOptionsMenu(true)

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
                    ?.navigate(
                        ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(
                            index
                        )
                    )
            }

            binding.shoeslist.addView(item.root)
        }

        binding.fabButton.setOnClickListener {
            shoeVM.clearShoeData()
            view?.findNavController()?.navigate(
                ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(
                    -1
                )
            )
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.loginFragment){
            SharedPreferencesManager.delete(MainActivity.LOGGEDIN)
            SharedPreferencesManager.delete(MainActivity.EMAIL)
            //Clear navigation backstack entry to avoid returning to ShoeListFragment on back press from Login after logout clicked
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.shoesListFragment, true).build()
            requireView().findNavController().navigate(R.id.loginFragment, null, navOptions)
        }

        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }
}