package com.example.hw3a2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp()
        }
    }
}

@Composable
fun QuizApp() {
    val questions = listOf(
        "What is the capital of France?" to "PARIS",
        "What is the capital of Germany?" to "BERLIN",
        "What is the capital of Italy?" to "ROME",
        "What is the capital of Spain?" to "MADRID",
        "What is the capital of Portugal?" to "LISBON",
        "What is the capital of the United Kingdom?" to "LONDON",
        "What is the capital of Russia?" to "MOSCOW",
        "What is the capital of Japan?" to "TOKYO",
        "What is the capital of China?" to "BEIJING",
        "What is the capital of India?" to "NEW DELHI",
        "What is the capital of Australia?" to "CANBERRA",
        "What is the capital of Canada?" to "OTTAWA",
        "What is the capital of the United States?" to "WASHINGTON DC",
        "What is the capital of Brazil?" to "BRASILIA",
        "What is the capital of Argentina?" to "BUENOS AIRES",
        "What is the capital of Mexico?" to "MEXICO CITY",
        "What is the capital of Egypt?" to "CAIRO",
        "What is the capital of South Africa?" to "PRETORIA",
        "What is the capital of South Korea?" to "SEOUL",
        "What is the capital of Turkey?" to "ANKARA"
    )

    var currentNum by remember { mutableStateOf(0) }
    var inputAnswer by remember { mutableStateOf(TextFieldValue("")) }
    var quizCompleted by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    val question = questions[currentNum].first
    val ans = questions[currentNum].second
    val snackbarHostState = remember { SnackbarHostState() }
    val questionsNum = questions.size


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!quizCompleted) {
            Text(
                text = "${currentNum + 1} / $questionsNum",
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = question,
                    modifier = Modifier.padding(20.dp)
                        .fillMaxWidth(),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center                )
            }

            TextField(
                value = inputAnswer,
                onValueChange = { inputAnswer = it },
                label = { Text("Enter your answer") },
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp)

            )

            Button(
                onClick = {
                    if (inputAnswer.text.uppercase() == ans.uppercase()) {
                        alertMessage = "Correct Answer"
                        if (currentNum == questions.size - 1) {
                            quizCompleted = true

                        } else {
                            currentNum++
                            inputAnswer = TextFieldValue("")


                        }
                    } else {
                        alertMessage = "Incorrect. Try again!"
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
            ) {
                Text("Submit")
            }
        } else {
            Text("Quiz Completed!!")
            Button(
                onClick = {
                    currentNum = 0
                    quizCompleted = false
                    inputAnswer = TextFieldValue("")
                },
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
            ) {
                Text("Restart")
            }
        }
    }

    LaunchedEffect(alertMessage) {
        if (alertMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(alertMessage)
            alertMessage = ""
        }
    }

    SnackbarHost(
        hostState = snackbarHostState
    )
}
