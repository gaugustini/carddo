package com.gaugustini.carddo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gaugustini.carddo.databinding.ListItemContactBinding
import com.gaugustini.carddo.model.Contact

class ContactAdapter(
    private val onClick: (Contact) -> Unit,
) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(DiffCallback<Contact>()) {

    class ContactViewHolder(
        private val binding: ListItemContactBinding,
        val onClick: (Contact) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                card.setOnClickListener {
                    onClick(binding.contact!!)
                }
            }
        }

        fun bind(item: Contact) {
            binding.apply {
                contact = item
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ListItemContactBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), onClick
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }

}
