package com.udacity.shoestore.shoe_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.custom_views.CustomShoeLayout
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.Shoe

class ShoelistFragment : Fragment() {

    companion object {
        fun newInstance() = ShoelistFragment()
    }

    private val shoeListingsViewModel: ShoelistViewModel by activityViewModels()
    private lateinit var binding: FragmentShoelistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoelist, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoeListingsViewModel.getShoeLiveData().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                createNewShoes(it)
            }
        }

        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            view.findNavController().navigate(ShoelistFragmentDirections.actionShoelistFragmentToShoeDetailsFragment())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun createNewShoes(shoes: List<Shoe>) {
        context?.let { context ->
            val shoeContainer = binding.list
            shoes.forEach { shoe ->
                val shoeLayout = CustomShoeLayout(context)
                shoeLayout.loadShoe(shoe)
                shoeContainer.addView(shoeLayout)
            }
        }
    }

}