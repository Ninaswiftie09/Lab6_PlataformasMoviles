package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import com.example.lab5.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                SplashScreen() 
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipeAppTheme {
        SplashScreen()
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEF5350)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Espacio para el icono
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Gray, CircleShape)
            )
            Text(
                text = "Chef Recipes",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEF5350))
            .padding(16.dp)
    ) {
        Text(text = "POPULAR RECIPES", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "SAVED RECIPES", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "SHOPPING LIST", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "SETTINGS", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Espacio para la imagen de perfil
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "HARRY TRUMAN", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = { Text("POPULAR RECIPES") },
            backgroundColor = Color(0xFFEF5350),
            contentColor = Color.White
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                RecipeCard(
                    title = "Prime Rib Roast",
                    description = "A classic and tender cut of beef...",
                    likes = 685,
                    comments = 107
                )
            }

        }
    }
}

@Composable
fun RecipeCard(
    title: String,
    description: String,
    likes: Int,
    comments: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column {
            // Espacio para la imagen de la receta
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = description, fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
                    Text(text = likes.toString(), modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Filled.Comment, contentDescription = null, tint = Color.Gray)
                    Text(text = comments.toString(), modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
fun RecipeDetailsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Espacio para la imagen de la receta
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Gray)
            )
            Text(text = "Prime Rib Roast", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "A classic and tender cut of beef...", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
            Text(text = "SHOPPING LIST", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
            // Lista de ingredientes
            Text(text = "1 Prime Rib Roast (8 pounds)", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
            Text(text = "1/2 cup balsamic vinegar", fontSize = 16.sp, modifier = Modifier.padding(top = 4.dp))
            Text(text = "1/4 teaspoon salt", fontSize = 16.sp, modifier = Modifier.padding(top = 4.dp))
            // Preparación
            Text(text = "PREPARATION", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
            Text(text = "Preheat oven to 350 degrees...", fontSize = 16.sp, modifier = Modifier.padding(top = 8.dp))
            // Sección de comentarios
            Text(text = "COMMENTS", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
            // Comentarios
            CommentItem(name = "Tom Klein", comment = "This prime rib roast was amazing!")
            CommentItem(name = "Sally Parker", comment = "My family loved it!")
        }
    }
}

@Composable
fun CommentItem(name: String, comment: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = name, fontWeight = FontWeight.Bold)
        Text(text = comment, fontSize = 16.sp)
    }
}

@Composable
fun RecipeDetailsCompleteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "YOU DID IT!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFEF5350)
                )
                Text(
                    text = "Let your friends know about it",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.Red, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Filled.Share, contentDescription = null, tint = Color.Blue, modifier = Modifier.size(32.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Filled.Comment, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}
