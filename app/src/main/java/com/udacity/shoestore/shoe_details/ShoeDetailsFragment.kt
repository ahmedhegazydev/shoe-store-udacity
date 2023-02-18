package com.udacity.shoestore.shoe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoe_list.ShoelistViewModel

class ShoeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ShoeDetailsFragment()
    }

    private val shoesViewModel: ShoelistViewModel by activityViewModels()
    lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        binding.lifecycleOwner = this
        binding.shoeListingsViewModel = shoesViewModel
        binding.shoe = Shoe()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.save.setOnClickListener {
            shoesViewModel.addShoe(shoesViewModel.shoe)
            view.findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoelistFragment())
        }

        binding.cancel.setOnClickListener {
            view.findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoelistFragment())
        }

    }

}