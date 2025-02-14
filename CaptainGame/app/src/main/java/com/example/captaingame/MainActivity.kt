package com.example.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptainGame()
        }
    }
}


@Composable
fun CaptainGame() {
    var treasuresFound by remember { mutableStateOf(0) }
    var moves by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("Start Your Adventure!") }
    var timeElapsed by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }

    // Timer Effect
    LaunchedEffect(Unit) {
        while (!gameOver) {
            delay(1000)
            timeElapsed++
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6)) // Light blue ocean background
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🏴‍☠️ Captain's Adventure 🏴‍☠️", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(10.dp))

        if (gameOver) {
            Text("Game Over!", fontSize = 20.sp, color = Color.Red)
            Text("Treasures Collected: $treasuresFound", fontSize = 18.sp)
            Text("Total Moves: $moves", fontSize = 18.sp)
            Text("Time Taken: $timeElapsed sec", fontSize = 18.sp)
        } else {
            Text("Treasures Found: $treasuresFound", fontSize = 18.sp)
            Text("Moves: $moves / 10", fontSize = 18.sp)
            Text("Time: $timeElapsed sec", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(message, fontSize = 18.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.ship),
                contentDescription = "Ship Icon",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                DirectionButton("Sail North") { move("North", ::updateGameState, treasuresFound, moves, { treasuresFound++ }, { moves++ }, { gameOver = true; message = "Game Over!" }) }
            }
            Row {
                DirectionButton("Sail West") { move("West", ::updateGameState, treasuresFound, moves, { treasuresFound++ }, { moves++ }, { gameOver = true; message = "Game Over!" }) }
                Spacer(modifier = Modifier.width(10.dp))
                DirectionButton("Sail East") { move("East", ::updateGameState, treasuresFound, moves, { treasuresFound++ }, { moves++ }, { gameOver = true; message = "Game Over!" }) }
            }
            Row {
                DirectionButton("Sail South") { move("South", ::updateGameState, treasuresFound, moves, { treasuresFound++ }, { moves++ }, { gameOver = true; message = "Game Over!" }) }
            }
        }

        // Restart Button
        Button(
            onClick = {
                treasuresFound = 0
                moves = 0
                timeElapsed = 0
                message = "Start Your Adventure!"
                gameOver = false
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Restart Game")
        }
    }
}

@Composable
fun DirectionButton(direction: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(direction)
    }
}

fun move(
    direction: String,
    updateState: (String, Int, Int, Boolean) -> Unit,
    treasuresFound: Int,
    moves: Int,
    increaseTreasure: () -> Unit,
    increaseMove: () -> Unit,
    gameOver: () -> Unit
) {
    if (moves < 10) {
        val foundTreasure = Random.nextBoolean()
        if (foundTreasure) increaseTreasure()
        increaseMove()
        updateState(direction, treasuresFound, moves, foundTreasure)
    } else {
        gameOver()
    }
}

fun updateGameState(direction: String, treasures: Int, moves: Int, foundTreasure: Boolean) {
    println("Moved $direction. Treasures: $treasures, Moves: $moves")
}
