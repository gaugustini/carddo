package com.gaugustini.carddo.database

import androidx.room.*
import com.gaugustini.carddo.model.Contact
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for [Contact].
 */
@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact WHERE id = :id")
    fun getContactById(id: Int): Flow<Contact>

    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getContacts(): Flow<List<Contact>>

}
