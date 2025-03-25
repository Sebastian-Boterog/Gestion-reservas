package com.GestionViajes.gestion_viajes.web.controller;

import com.GestionViajes.gestion_viajes.domain.dto.ViajeDTO;
import com.GestionViajes.gestion_viajes.domain.service.ViajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    // Obtener todos los viajes
    @Operation(summary = "Obtener todos los viajes", description = "Retorna la lista de viajes disponibles en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay viajes disponibles"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ViajeDTO>> getAllViajes() {
        List<ViajeDTO> viajes = viajeService.obtenerTodos();
        if (viajes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(viajes);
    }

    // Obtener viaje por ID
    @Operation(summary = "Obtener viaje por ID", description = "Busca un viaje en el sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje encontrado"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> getViajeById(@PathVariable Long id) {
        return viajeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo viaje
    @Operation(summary = "Registrar un nuevo viaje", description = "Registra un nuevo viaje en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viaje registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes")
    })
    @PostMapping
    public ResponseEntity<ViajeDTO> createViaje(@RequestBody ViajeDTO viajeDTO) {
        ViajeDTO nuevoViaje = viajeService.guardar(viajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoViaje);
    }

    // Actualizar un viaje
    @Operation(summary = "Actualizar un viaje", description = "Actualiza los datos de un viaje existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateViaje(@PathVariable Long id, @RequestBody ViajeDTO viajeDTO) {
        if (!viajeService.existeViaje(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Viaje no encontrado.");
        }
        viajeDTO.setId(id);
        return ResponseEntity.ok(viajeService.actualizar(viajeDTO));
    }

    // Eliminar un viaje
    @Operation(summary = "Eliminar un viaje", description = "Elimina un viaje del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Viaje eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteViaje(@PathVariable Long id) {
        if (!viajeService.existeViaje(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Viaje no encontrado.");
        }
        viajeService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
