package com.surya.roche_assessment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.surya.roche_assessment.ui.theme.Roche_AssessmentTheme

class Welcome_Screen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Roche_AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val username = intent.getStringExtra("Username").toString()
                    Top(username)
                }
            }
        }
    }

    fun signOut() {
        AuthUI.getInstance().signOut(this)
        this.startActivity(Intent(this, MainActivity::class.java))
    }

    @Composable
    fun Top(name: String, viewModel: TaskViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
        var text by remember { mutableStateOf("") }
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.Start) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter Task") }
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            ExtendedFloatingActionButton(text = { Text("Add Task") },
                icon = {
                    Icon(
                        Icons.Filled.Create,
                        contentDescription = "Add Task"
                    )
                },
                onClick = {viewModel.addTask(text)},
                //onClick = { add_Task(name, text) },
                modifier = Modifier.padding(vertical = 25.dp, horizontal = 10.dp)
            )
            Task_List()
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            ExtendedFloatingActionButton(text = { Text("Logout") }, onClick = { signOut() },
                icon = { Icon(Icons.Filled.Close, contentDescription = "Sign Out") },
                modifier = Modifier.padding(vertical = 80.dp, horizontal = 10.dp)
            )
        }

    }

         @Composable
    fun Task_List(viewModel: TaskViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
        val tasksLiveData by viewModel.getAll().observeAsState(listOf())
        LazyColumn(
            modifier = Modifier.padding(vertical = 40.dp)
        ){
            items(tasksLiveData) { task ->
                if (task.UID == FirebaseAuth.getInstance().currentUser?.uid.toString()) {
                    Task_List_Item(
                        taskName = task.Task,
                        checked = task.Checked,
                        onCheckedChange = {checked -> viewModel.onCheckedTask(task)},
                        onClose = { viewModel.delete(task) }, Task = task)
                }
            }
        }
    }


    }


