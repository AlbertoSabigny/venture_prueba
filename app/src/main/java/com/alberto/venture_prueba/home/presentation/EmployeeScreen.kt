package com.alberto.venture_prueba.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.alberto.venture_prueba.R
import com.alberto.venture_prueba.auth.data.remote.Empleado
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeScreen(viewModel: EmployeeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Surface(
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.75).dp)
                    .fillMaxHeight(),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) { Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        model = uiState.foto,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier.size(60.dp)
                            )
                        },
                        error = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Foto de perfil no disponible",
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = uiState.nombreCompleto,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                    Divider(modifier = Modifier.padding(bottom = 16.dp))
                    listOf(
                        MenuItem("Datos de compañía", Icons.Default.Info),
                        MenuItem("Supervisores", Icons.Default.AccountBox),
                        MenuItem("Zonas", Icons.Default.Place),
                        MenuItem("Estaciones", Icons.Default.LocationOn),
                        MenuItem("Empleados", Icons.Default.Person),
                        MenuItem("Reportes", Icons.Default.Email),
                        MenuItem("Historial de auditoría", Icons.Default.DateRange),
                        MenuItem("Cambiar contraseña", Icons.Default.Lock),
                        MenuItem("Configuración", Icons.Default.Settings)
                    ).forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { /* */ },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = item.title,
                                color = Color.Black
                            )
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { /*  */ },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Cerrar sesión",
                            color = Color.Black
                        )
                    }
                }
            }
        },
        scrimColor = Color.Black.copy(alpha = 0.32f)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color(0xFFC0A0FF)
                            )
                        }
                    },
                    actions = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            SubcomposeAsyncImage(
                                model = R.drawable.login_prueba,
                                contentDescription = "Logo",
                                modifier = Modifier.size(120.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* TODO */ },
                    containerColor = Color(0xFFC0A0FF)
                ) {
                    Icon(Icons.Filled.Add, "Agregar")
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    uiState.error != null -> {
                        Text(
                            text = "Error: ${uiState.error}",
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Black
                        )
                    }
                    uiState.employees != null -> {
                        LazyColumn {
                            items(
                                items = uiState.employees!!,
                                key = { empleado -> empleado.idEmpleado }
                            ) { empleado ->
                                EmployeeItem(empleado)
                            }
                        }
                    }
                }
            }
        }
    }
}

data class MenuItem(val title: String, val icon: ImageVector)
@Composable
fun EmployeeItem(empleado: Empleado) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ID Empleado: ${empleado.idEmpleado}",
                color = Color.Gray
            )
            Text(
                text = "${empleado.nombre} ${empleado.apellidoPat} ${empleado.apellidoMat}",
                color = Color.Black
            )
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}