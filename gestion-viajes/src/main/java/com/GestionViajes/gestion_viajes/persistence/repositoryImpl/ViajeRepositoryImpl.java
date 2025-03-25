package com.GestionViajes.gestion_viajes.persistence.repositoryImpl;

import com.GestionViajes.gestion_viajes.domain.dto.ViajeDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Viaje;
import com.GestionViajes.gestion_viajes.domain.repository.ViajeRepository;
import com.GestionViajes.gestion_viajes.persistence.crud.ViajeCrudRepository;
import com.GestionViajes.gestion_viajes.persistence.mapper.ViajeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ViajeRepositoryImpl implements ViajeRepository {

    private final ViajeCrudRepository viajeCrudRepository;
    private final ViajeMapper viajeMapper;

    public ViajeRepositoryImpl(ViajeCrudRepository viajeCrudRepository, ViajeMapper viajeMapper) {
        this.viajeCrudRepository = viajeCrudRepository;
        this.viajeMapper = viajeMapper;
    }

    @Override
    public List<ViajeDTO> findAll() {
        return viajeCrudRepository.findAll().stream()
                .map(viajeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ViajeDTO> findById(Long id) {
        return viajeCrudRepository.findById(id).map(viajeMapper::toDto);
    }

    @Override
    public ViajeDTO save(ViajeDTO viajeDTO) {
        Viaje viaje = viajeMapper.toEntity(viajeDTO);
        return viajeMapper.toDto(viajeCrudRepository.save(viaje));
    }

    @Override
    public ViajeDTO update(ViajeDTO viajeDTO) {
        if (!viajeCrudRepository.existsById(viajeDTO.getId())) {
            throw new RuntimeException("El viaje no existe.");
        }
        Viaje viaje = viajeMapper.toEntity(viajeDTO);
        return viajeMapper.toDto(viajeCrudRepository.save(viaje));
    }

    @Override
    public void delete(Long id) {
        viajeCrudRepository.deleteById(id);
    }

    @Override
    public long count() {
        return viajeCrudRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return viajeCrudRepository.existsById(id);
    }
    
}
