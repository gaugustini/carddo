package com.gaugustini.carddo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gaugustini.carddo.R
import com.gaugustini.carddo.databinding.FragmentHomeBinding
import com.gaugustini.carddo.ui.ContactViewModel
import com.gaugustini.carddo.ui.adapter.ContactAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: ContactViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setAdapter()
        fabClick()

        return binding.root
    }

    private fun setAdapter() {
        val adapter = ContactAdapter(
            onClick = {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeToEntry(
                        getString(R.string.title_fragment_edit), it.id
                    )
                )
            }
        )

        binding.contactList.adapter = adapter

        viewModel.getAll().observe(this.viewLifecycleOwner) {
            binding.hasContacts = it.isEmpty()
            adapter.submitList(it)
        }
    }

    private fun fabClick() {
        binding.fab.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections.actionHomeToEntry(getString(R.string.title_fragment_new))
            )
        }
    }

}
