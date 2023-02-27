package com.surya.roche_assessment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        TaskDatabase::class.java, "todo_list_database"
    ).build()
    fun addTask(TaskName: String){
        GlobalScope.launch(Dispatchers.IO){
            db.TaskDAO().insert(
                TaskItem(
                    UID = FirebaseAuth.getInstance().currentUser?.uid.toString(),
                    Name = FirebaseAuth.getInstance().currentUser?.displayName,
                    Task = TaskName, Checked = false
                )
            )
        }
    }
    fun getAll(): LiveData<List<TaskItem>> {
        return db.TaskDAO().getAll()
    }
    fun delete(Taskitem: TaskItem){
        GlobalScope.launch(Dispatchers.IO){ db.TaskDAO().delete(Taskitem) }
    }
    fun onCheckedTask(Taskitem: TaskItem) {
        Taskitem.Checked = if (Taskitem.Checked) true else false
        GlobalScope.launch(Dispatchers.IO) { db.TaskDAO().updateTaskItemChecked(id = Taskitem.ID, checked = Taskitem.Checked)}
        }
}