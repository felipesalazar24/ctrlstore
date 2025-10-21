package com.example.ctrlstore.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var nombre = mutableStateOf("")
    var correo = mutableStateOf("")
    var clave = mutableStateOf("")
    var mensaje = mutableStateOf("")

    fun enviarFormulario() {
        if (nombre.value.isNotBlank() && correo.value.isNotBlank() && clave.value.isNotBlank()) {
            mensaje.value = "Formulario enviado por ${nombre.value} <${correo.value}> <${clave.value}>"
        } else {
            mensaje.value = "Por favor completa todos los campos."
        }
    }
}

