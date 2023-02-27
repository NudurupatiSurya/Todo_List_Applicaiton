package com.surya.todo_list_assessment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseAuth

@Entity(tableName = "TaskItemTable")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0,
    @ColumnInfo()
    val UID: String = FirebaseAuth.getInstance().currentUser?.uid.toString(),
    @ColumnInfo
    val Name: String? = FirebaseAuth.getInstance().currentUser?.displayName,
    @ColumnInfo
    val Task: String,
    @ColumnInfo
    var Checked: Boolean
){
}