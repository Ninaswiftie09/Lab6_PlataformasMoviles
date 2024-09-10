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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.desing.ui.theme.DesingTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


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
                    composable("arroz_detail") { ArrozDetailScreen(navController) }
                    composable("add_comment") { AddCommentScreen(navController) }
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
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Appetizers", "Entrees", "Dessert").forEach { category ->
                Text(
                    text = category,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (category == "Entrees") Color.Black else Color.Gray,
                    modifier = Modifier.clickable { }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val featuredIndex = 2
            Image(
                painter = painterResource(id = recetas[featuredIndex]),
                contentDescription = nombresRecetas[featuredIndex],
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .clickable { navController.navigate("arroz_detail") },  // Navegar a la pantalla de detalle de arroz
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = nombresRecetas[featuredIndex],
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "⏱ ${tiemposCoccion[featuredIndex]}")
                Text(text = "❤️ ${likesRecetas[featuredIndex]}")
                Text(text = "⭐ ${reviewsRecetas[featuredIndex]} Reviews")
            }
        }

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
                                navController.navigate("arroz_detail")
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





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DesingTheme {
        val navController = rememberNavController()
        SplashScreen(navController)
    }
}


@Composable
fun RecipeDetailScreen(
    receta: Int,
    nombreReceta: String,
    descripcion: String,
    estrellas: Int,
    tiempo: String,
    likes: Int,
    ingredientes: List<String>,
    preparacion: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = receta),
            contentDescription = nombreReceta,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = nombreReceta,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = descripcion,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "⏱ $tiempo")
            Text(text = "❤️ $likes Likes")
            Text(text = "⭐ $estrellas Estrellas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingredientes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        ingredientes.forEach { ingrediente ->
            Text(
                text = "- $ingrediente",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Preparación",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        preparacion.forEachIndexed { index, paso ->
            Text(
                text = "${index + 1}. $paso",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}



@Composable
fun ArrozDetailScreen(navController: NavHostController) {
    val nombreReceta = "Arroz"
    val descripcion = "Un delicioso arroz al estilo tradicional, perfecto como acompañamiento para cualquier plato."
    val estrellas = 4
    val tiempo = "30min"
    val likes = 120
    val ingredientes = listOf("1 taza de arroz", "2 tazas de agua", "1 cucharadita de sal", "2 cucharadas de aceite")
    val preparacion = listOf(
        "Lavar bien el arroz bajo agua fría.",
        "En una olla, calentar el aceite a fuego medio.",
        "Agregar el arroz y saltearlo por 2-3 minutos.",
        "Añadir el agua y la sal, llevar a ebullición.",
        "Reducir el fuego, tapar la olla y cocinar a fuego lento por 18-20 minutos.",
        "Retirar del fuego y dejar reposar tapado por 5 minutos antes de servir."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RecipeDetailScreen(
            receta = R.drawable.arroz,
            nombreReceta = nombreReceta,
            descripcion = descripcion,
            estrellas = estrellas,
            tiempo = tiempo,
            likes = likes,
            ingredientes = ingredientes,
            preparacion = preparacion,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Comentarios",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(5) { index ->
                CommentRow(
                    userName = "Usuario $index",
                    commentDate = "2024-09-10",
                    commentText = "Comentario $index: Muy buena receta, la recomiendo."
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("add_comment") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Agregar Comentario")
        }
    }
}


@Composable
fun CommentRow(

    userName: String,
    commentDate: String,
    commentText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = userName,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = commentDate,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = commentText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArrozDetailScreenPreview() {
    val navController = rememberNavController()
    ArrozDetailScreen(navController = navController)
}



@Composable
fun AddCommentScreen(navController: NavHostController) {
    var commentText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agregar Comentario",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = commentText,
            onValueChange = { commentText = it },
            label = { Text("Escribe tu comentario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.popBackStack() // Regresar a la pantalla anterio
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Enviar Comentario")
        }
    }
}
