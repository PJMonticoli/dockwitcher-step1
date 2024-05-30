package com.hotakakademy.dockwitcher.controllers
import com.hotakakademy.dockwitcher.DTO.ConductorDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import com.hotakakademy.dockwitcher.services.ConductorService
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
@Controller
class ConductoresController (
    private val conductorService: ConductorService
) {

    // Creo un service para mantener el patron de diseño
    // En el controller manejo las peticiones HTTP(GET,POST,PUT,ETC)
    // En el service tengo la logica de negocio
    @GetMapping("/conductores")
    fun listado(model: Model): String {
        val conductores = conductorService.findAll()
        model.addAttribute("conductores", conductores)
        return "listadoconductores"
    }

    @GetMapping("/conductores/registrar")
    fun nuevo(model: Model): String {
        return "registrarconductor"
    }

    @PostMapping("/conductores/registrar")
    fun create(@ModelAttribute conductorDto: ConductorDto): String {
        return try {
            conductorService.createConductor(conductorDto)
            "redirect:/conductores"
        } catch (e: Exception) {
            // Log the exception for debugging purposes
            println("Error: ${e.message}")
            e.printStackTrace()
            "error"
        }
    }

}
