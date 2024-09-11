package com.example.desing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.desing.ui.theme.DesingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesingTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable("splash_screen") { SplashScreen(navController) }
                    composable("menu") { MenuScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("recipe_detail") { RecipeDetailScreen(navController) }
                    composable("add_comment") { AddCommentScreen(navController) }
                    composable("dialog_screen") { DialogScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEF5350))
            .clickable { navController.navigate("menu") },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.sombrero_chef),
                contentDescription = "Chef Hat",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Chef Recipes",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun MenuScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF6F61))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Popular Recipes",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .clickable { navController.navigate("home") }
                    .padding(vertical = 16.dp)
            )
            Text(
                text = "Saved Recipes",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Shopping List",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "Settings",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.taylor),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Taylor Swift",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val recetas = listOf(
        R.drawable.arroz,
        R.drawable.cafe,
        R.drawable.lasagna,
        R.drawable.licuado,
        R.drawable.panfrijol,
        R.drawable.pollo
    )
    val nombresRecetas = listOf(
        "Arroz", "Café", "Lasagna", "Licuado de Banano", "Pan con Frijol", "Pollo Asado"
    )
    val tiemposCoccion = listOf(
        "30min", "10min", "1h 30min", "15min", "20min", "45min"
    )
    val likesRecetas = listOf(
        120, 98, 210, 75, 50, 180
    )
    val reviewsRecetas = listOf(
        12, 10, 23, 7, 4, 19
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Recetas Populares",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recetas.size) { index ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(150.dp)
                        .clickable {
                            if (nombresRecetas[index] == "Arroz") {
                                navController.navigate("recipe_detail")
                            }
                        }
                ) {
                    Image(
                        painter = painterResource(id = recetas[index]),
                        contentDescription = nombresRecetas[index],
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = nombresRecetas[index],
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}



@Composable
fun RecipeDetailScreen(navController: NavHostController) {
    val (commentText, setCommentText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen de la receta
        Image(
            painter = painterResource(id = R.drawable.arroz),
            contentDescription = "Arroz",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Titulo de la receta
        Text(
            text = "Arroz",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Sección de comentarios
        Text(
            text = "Comentarios:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Comentarios (simulación)
        Column {
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.taylor),
                    contentDescription = "Usuario",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "Juan Pérez", fontWeight = FontWeight.Bold)
                    Text(text = "¡Delicioso!", fontSize = 14.sp)
                    Text(text = "01/01/2024", fontSize = 12.sp, color = Color.Gray)
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección para agregar un comentario
        TextField(
            value = commentText,
            onValueChange = { setCommentText(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFE0E0E0))
                .padding(8.dp),
            singleLine = false,
            label = { Text("Tu comentario") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Acción para enviar el comentario */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar Comentario")
        }
    }
}
@Composable
fun AddCommentScreen(navController: NavHostController) {
    val (commentText, setCommentText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agregar Comentario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = commentText,
            onValueChange = { setCommentText(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFE0E0E0))
                .padding(8.dp),
            singleLine = false,
            label = { Text("Escribe tu comentario aquí") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Acción para enviar el comentario */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar Comentario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("recipe_detail") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun DialogScreen(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("¡Lo lograste!") },
            text = {
                Column {
                    Text("¡Tu receta ha sido guardada exitosamente!")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            // Acción para compartir
                            showDialog = false
                        }) {
                            Text("Let your friends know about it")
                        }
                        Button(onClick = {
                            navController.navigate("add_comment")
                            showDialog = false
                        }) {
                            Text("Leave a review")
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

    Button(onClick = { showDialog = true }) {
        Text("Show Dialog")
    }
}
