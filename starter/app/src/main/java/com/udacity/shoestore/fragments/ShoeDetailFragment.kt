package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.utils.ShoeActionHandler
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment(), ShoeActionHandler {

    private val shoeVM: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding
    private var index = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
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
            index = ShoeDetailFragmentArgs.fromBundle(it).shoeId
            if (index != -1 ) {
                shoeVM.shoesList.value?.let { shoesList ->
                    val shoe = shoesList[index]
                    shoeVM.shoeName.value = shoe.name
                    shoeVM.shoeBrand.value = shoe.company
                    shoeVM.shoeSize.value = (shoe.size.toString())
                    shoeVM.shoeDescription.value = shoe.description
                }

            }
        }
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu)
    {
        val item: MenuItem  = menu.findItem (R.id.loginFragment);
        item.isVisible = false;
    }

    override fun submitClicked(view: View) {
        shoeVM.submitClicked(index)
        view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoesListFragment)
    }

    override fun cancelClicked(view: View) {
        shoeVM.cancelClicked()
        view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoesListFragment)
    }

}