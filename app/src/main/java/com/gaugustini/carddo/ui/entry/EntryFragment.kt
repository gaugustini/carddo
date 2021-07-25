package com.gaugustini.carddo.ui.entry

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gaugustini.carddo.R
import com.gaugustini.carddo.databinding.FragmentEntryBinding
import com.gaugustini.carddo.ui.ContactViewModel
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryFragment : Fragment() {

    private val viewModel: ContactViewModel by activityViewModels()
    private val args: EntryFragmentArgs by navArgs()
    private lateinit var binding: FragmentEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryBinding.inflate(inflater, container, false)

        setContact(args.contactId)
        clickListeners()

        if (args.contactId > 0) {
            setHasOptionsMenu(true)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_entry, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                showConfirmationDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.delete(binding.contact!!)
                findNavController().navigateUp()
            }
            .show()
    }

    private fun setContact(id: Int) {
        if (id > 0) {
            viewModel.getContact(id).observe(this.viewLifecycleOwner) {
                binding.contact = it
                viewModel.color = it.color
            }
        }
    }

    private fun clickListeners() {
        colorClick()
        saveClick()
    }

    private fun colorClick() {
        binding.color.setOnClickListener { colorPicker() }
    }

    private fun colorPicker() {
        MaterialColorPickerDialog
            .Builder(requireContext())
            .setTitle(getString(R.string.pick_color))
            .setColorListener { _, colorHex ->
                viewModel.color = Color.parseColor(colorHex.uppercase())
                binding.colorLabel.boxBackgroundColor = viewModel.color
            }
            .show()
    }

    private fun saveClick() {
        binding.btnSave.setOnClickListener { entryContact() }
    }

    private fun entryContact() {
        if (isEntryValid()) {
            viewModel.entryContact(
                args.contactId,
                binding.name.text.toString(),
                binding.job.text.toString(),
                binding.phone.text.toString(),
                binding.mail.text.toString(),
                binding.company.text.toString(),
                binding.site.text.toString()
            )
            findNavController().navigateUp()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.name.text.toString(),
            binding.job.text.toString(),
            binding.phone.text.toString(),
            binding.mail.text.toString(),
            binding.company.text.toString(),
            binding.site.text.toString()
        )
    }

}
