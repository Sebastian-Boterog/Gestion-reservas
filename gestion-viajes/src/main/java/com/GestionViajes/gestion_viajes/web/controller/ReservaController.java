package com.GestionViajes.gestion_viajes.web.controller;

import com.GestionViajes.gestion_viajes.domain.dto.ReservaDTO;
import com.GestionViajes.gestion_viajes.domain.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Obtener todas las reservas
    @Operation(summary = "Obtener todas las reservas", description = "Retorna la lista de reservas registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay reservas registradas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        List<ReservaDTO> reservas = reservaService.obtenerTodas();
        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(reservas);
    }

    // Obtener reserva por ID
    @Operation(summary = "Obtener reserva por ID", description = "Busca una reserva en el sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva reserva
    @Operation(summary = "Registrar una nueva reserva", description = "Registra una nueva reserva en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes")
    })
    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO nuevaReserva = reservaService.guardar(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    // Actualizar una reserva
    @Operation(summary = "Actualizar una reserva", description = "Actualiza los datos de una reserva existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        if (!reservaService.existeReserva(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Reserva no encontrada.");
        }
        reservaDTO.setId(id);
        return ResponseEntity.ok(reservaService.actualizar(reservaDTO));
    }

    // Eliminar una reserva
    @Operation(summary = "Eliminar una reserva", description = "Elimina una reserva del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable Long id) {
        if (!reservaService.existeReserva(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Reserva no encontrada.");
        }
        reservaService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
