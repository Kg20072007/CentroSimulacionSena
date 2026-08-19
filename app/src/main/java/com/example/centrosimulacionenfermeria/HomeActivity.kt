package com.example.centrosimulacionenfermeria

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ModuleCategory {
    TODOS,
    AMBIENTE,
    EQUIPOS,
    INSUMOS,
    REPORTES
}

data class SimulationModule(
    val id: String,
    val title: String,
    val category: ModuleCategory,
    val icon: ImageVector,
    val route: String
)

data class SimulationEvent(
    val title: String,
    val description: String
)

private val SenaGreen = Color(0xFF006837)
private val SenaOrange = Color(0xFFE85D04)

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimulationHomeScreenContent(
                onLogout = {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                },
                onNavigateToModule = { route ->
                    when (route) {
                        "servicios" -> startActivity(Intent(this, ServiciosActivity::class.java))
                        "contacto" -> startActivity(Intent(this, ContactoActivity::class.java))
                        "simulaciones", "cronograma", "equipos", "inventario", "reportes" -> {
                            startActivity(Intent(this, SimulacionesActivity::class.java))
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimulationHomeScreenContent(
    onLogout: () -> Unit = {},
    onNavigateToModule: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(ModuleCategory.TODOS) }

    val allModules = remember {
        listOf(
            SimulationModule(
                id = "1",
                title = "Servicios del Centro",
                category = ModuleCategory.AMBIENTE,
                icon = Icons.Default.MedicalServices,
                route = "servicios"
            ),
            SimulationModule(
                id = "2",
                title = "Uso de Equipos",
                category = ModuleCategory.EQUIPOS,
                icon = Icons.Default.Build,
                route = "equipos"
            ),
            SimulationModule(
                id = "3",
                title = "Simulaciones y Escenarios",
                category = ModuleCategory.INSUMOS,
                icon = Icons.Default.Science,
                route = "simulaciones"
            ),
            SimulationModule(
                id = "4",
                title = "Contacto y Soporte",
                category = ModuleCategory.REPORTES,
                icon = Icons.Default.ContactPhone,
                route = "contacto"
            )
        )
    }

    val displayedModules = allModules.filter {
        val categoria = selectedCategory == ModuleCategory.TODOS || it.category == selectedCategory
        val busqueda = it.title.contains(searchQuery, ignoreCase = true)
        categoria && busqueda
    }

    val events = listOf(
        SimulationEvent("Práctica de Urgencias", "Simulación de atención a paciente crítico."),
        SimulationEvent("Capacitación en RCP", "Entrenamiento con maniquíes de alta fidelidad."),
        SimulationEvent("Simulación Materno Infantil", "Escenarios clínicos reales para aprendices.")
    )

    // Las 8 imágenes de res/drawable en 2 filas
    val galleryRow1 = listOf(
        R.drawable.g1,
        R.drawable.g2,
        R.drawable.g3,
        R.drawable.g4
    )

    val galleryRow2 = listOf(
        R.drawable.g5,
        R.drawable.g6,
        R.drawable.g7,
        R.drawable.g8
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Centro de Simulación",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SenaGreen)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF7F7F7))
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SenaGreen)
                        .padding(vertical = 30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Centro de Simulación",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Sistema de gestión del ambiente, equipos e insumos",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Search, null) },
                        placeholder = { Text("Buscar módulo...") },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }

            item {
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(ModuleCategory.entries.toTypedArray()) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category.name) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = SenaGreen,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Módulos Principales",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SenaGreen
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    displayedModules.forEach { module ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 14.dp)
                                .clickable { onNavigateToModule(module.route) },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(56.dp)
                                        .background(SenaGreen.copy(alpha = 0.10f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = module.icon,
                                        contentDescription = null,
                                        tint = SenaGreen,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = module.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Accede al módulo correspondiente.",
                                        color = Color.Gray,
                                        fontSize = 13.sp
                                    )
                                }

                                Button(
                                    onClick = { onNavigateToModule(module.route) },
                                    colors = ButtonDefaults.buttonColors(containerColor = SenaGreen)
                                ) {
                                    Text("Ingresar")
                                }
                            }
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Eventos del Centro de Simulación",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SenaGreen
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(events) { event ->
                            Card(
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(150.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(5.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Event,
                                        contentDescription = null,
                                        tint = SenaOrange,
                                        modifier = Modifier.size(32.dp)
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = event.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = event.description,
                                        color = Color.Gray,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Galería del Centro de Simulación",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = SenaGreen
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Primera fila de imágenes (g1, g2, g3, g4)
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(galleryRow1) { image ->
                            Card(
                                modifier = Modifier.size(180.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Segunda fila de imágenes (g5, g6, g7, g8)
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(galleryRow2) { image ->
                            Card(
                                modifier = Modifier.size(180.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = SenaGreen)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Centro de Simulación SENA",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Fortaleciendo el aprendizaje mediante escenarios clínicos, equipos biomédicos e insumos para la formación de los aprendices.",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}