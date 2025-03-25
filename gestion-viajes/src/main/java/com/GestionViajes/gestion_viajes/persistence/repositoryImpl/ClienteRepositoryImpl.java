package com.GestionViajes.gestion_viajes.persistence.repositoryImpl;


import com.GestionViajes.gestion_viajes.domain.dto.ClienteDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Cliente;
import com.GestionViajes.gestion_viajes.domain.repository.ClienteRepository;
import com.GestionViajes.gestion_viajes.persistence.crud.ClienteCrudRepository;
import com.GestionViajes.gestion_viajes.persistence.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClienteMapper clienteMapper;  
    
    @Override
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDTO> findById(Long id) {
        return clienteCrudRepository.findById(id)
                .map(clienteMapper::toDto);
    }

    @Override
    public Optional<ClienteDTO> findByCorreo(String correo) {
        return clienteCrudRepository.findByCorreo(correo)
                .map(clienteMapper::toDto);
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        if (!existsById(cliente.getId())) {
            Cliente savedCliente = clienteCrudRepository.save(cliente);
            return clienteMapper.toDto(savedCliente);
        }
        throw new IllegalArgumentException("El cliente ya existe");
    }

    @Override
    public ClienteDTO update(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        if (existsById(cliente.getId())) {
            Cliente updatedCliente = clienteCrudRepository.save(cliente);
            return clienteMapper.toDto(updatedCliente);
        }
        throw new IllegalArgumentException("El cliente no existe");
    }

    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            clienteCrudRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El cliente no existe");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return clienteCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return clienteCrudRepository.count();
    }
}


