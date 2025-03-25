package com.GestionViajes.gestion_viajes.web.controller;

import com.GestionViajes.gestion_viajes.domain.dto.ClienteDTO;
import com.GestionViajes.gestion_viajes.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Obtener todos los clientes
    @Operation(summary = "Obtener todos los clientes", description = "Retorna la lista de clientes registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay clientes registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por ID
    @Operation(summary = "Obtener cliente por ID", description = "Busca un cliente en el sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Obtener cliente por correo
    @Operation(summary = "Obtener cliente por correo", description = "Busca un cliente en el sistema por su correo electrónico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/correo/{correo}")
    public ResponseEntity<ClienteDTO> getClienteByCorreo(@PathVariable String correo) {
        return clienteService.obtenerPorCorreo(correo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo cliente
    @Operation(summary = "Registrar un nuevo cliente", description = "Registra un nuevo cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "El correo ya está en uso o los datos son inválidos")
    })
    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody ClienteDTO clienteDTO) {
        // Validar si el correo ya está en uso
        if (clienteService.obtenerPorCorreo(clienteDTO.getCorreo()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El correo ya está en uso.");
        }
        ClienteDTO nuevoCliente = clienteService.guardar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // Actualizar un cliente
    @Operation(summary = "Actualizar un cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        if (!clienteService.existeCliente(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Cliente no encontrado.");
        }
        clienteDTO.setId(id);
        return ResponseEntity.ok(clienteService.actualizar(clienteDTO));
    }

    // Eliminar un cliente
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente del sistema por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        if (!clienteService.existeCliente(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Cliente no encontrado.");
        }
        clienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
