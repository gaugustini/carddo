package com.gaugustini.carddo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A class used to represent each contact.
 */
@Entity(tableName = "contact")
data class Contact(
    val name: String,
    val job: String,
    val phone: String,
    val mail: String,
    val company: String,
    val site: String,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
