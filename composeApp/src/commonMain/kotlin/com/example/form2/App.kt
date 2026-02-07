package com.example.form2
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert

@Composable
@Preview
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text("Registro de usuario") }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Guardado: $nombre ($email)")
                        }
                    }
                ) {
                    Text("Guardar")
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Usuarios registrados:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                val usuariosSimulados = listOf(
                    "Juan Pérez - juan@ejemplo.com",
                    "María García - maria@ejemplo.com",
                    "Pedro López - pedro@ejemplo.com",
                    "Ana Martínez - ana@ejemplo.com",
                    "Luis Rodríguez - luis@ejemplo.com"
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(usuariosSimulados) { usuario ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Cambia a 'true' para ver el menú abierto en el Preview
                            var menuExpandido by remember { mutableStateOf(false) }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = usuario,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Box {
                                    IconButton(onClick = { menuExpandido = true }) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = "Opciones"
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = menuExpandido,
                                        onDismissRequest = { menuExpandido = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Editar") },
                                            onClick = {
                                                menuExpandido = false
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Editando: $usuario")
                                                }
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Eliminar") },
                                            onClick = {
                                                menuExpandido = false
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Eliminando: $usuario")
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}