package com.GestionViajes.gestion_viajes.domain.service;

import com.GestionViajes.gestion_viajes.domain.dto.ClienteDTO;
import com.GestionViajes.gestion_viajes.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteDTO> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<ClienteDTO> obtenerPorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        return clienteRepository.save(clienteDTO);
    }

    public ClienteDTO actualizar(ClienteDTO clienteDTO) {
        return clienteRepository.update(clienteDTO);
    }

    public void eliminar(Long id) {
        clienteRepository.delete(id);
    }

    public boolean existeCliente(Long id) {
        return clienteRepository.existsById(id);
    }

    public long contarClientes() {
        return clienteRepository.count();
    }
}
