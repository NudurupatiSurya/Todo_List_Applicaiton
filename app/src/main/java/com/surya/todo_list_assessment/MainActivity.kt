package com.surya.todo_list_assessment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.surya.todo_list_assessment.ui.theme.todo_list_AssessmentTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            todo_list_AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (FirebaseAuth.getInstance().currentUser != null) {
                        this.startActivity(Intent(this, Welcome_Screen::class.java))
                    }
                    else{
                        this.startActivity(Intent(this, Login_Activity::class.java))
                    }
                }
            }
        }
    }

}


