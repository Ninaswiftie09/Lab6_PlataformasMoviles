package com.example.desing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesingTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable("splash_screen") { SplashScreen(navController) }
                    composable("home") { HomeScreen() }
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
            .clickable { navController.navigate("home") },
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
fun HomeScreen() {
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
                    .background(Color.LightGray),
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
                        .clickable { }
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
