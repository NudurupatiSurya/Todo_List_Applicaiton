package com.surya.todo_list_assessment

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.firebase.auth.FirebaseAuth
val UID: String = FirebaseAuth.getInstance().currentUser?.uid.toString()
@Dao
interface TaskDAO {

    @Query("SELECT * from TaskItemTable")
    fun getAll(): LiveData<List<TaskItem>>
    @Insert
    suspend fun insert(item:TaskItem)
    @Update
    suspend fun update(item:TaskItem)
    @Delete
    suspend fun delete(ID:TaskItem)

    @Query("UPDATE TaskItemTable SET Checked = :checked WHERE ID = :id")
    suspend fun updateTaskItemChecked(id: Int, checked: Boolean)
}