package com.GestionViajes.gestion_viajes.web.controller;

import com.GestionViajes.gestion_viajes.domain.dto.PagoDTO;
import com.GestionViajes.gestion_viajes.domain.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Operation(summary = "Obtener todos los pagos", description = "Retorna la lista de pagos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay pagos registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAllPagos() {
        List<PagoDTO> pagos = pagoService.obtenerTodos();
        if (pagos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Obtener un pago por ID", description = "Busca un pago en el sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Long id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Registrar un nuevo pago", description = "Registra un nuevo pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes")
    })
    @PostMapping
    public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO) {
        PagoDTO nuevoPago = pagoService.guardar(pagoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

    @Operation(summary = "Actualizar un pago", description = "Actualiza los datos de un pago existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        if (!pagoService.existePago(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Pago no encontrado.");
        }
        pagoDTO.setId(id);
        return ResponseEntity.ok(pagoService.actualizar(pagoDTO));
    }

    @Operation(summary = "Eliminar un pago", description = "Elimina un pago del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pago eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePago(@PathVariable Long id) {
        if (!pagoService.existePago(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Pago no encontrado.");
        }
        pagoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
