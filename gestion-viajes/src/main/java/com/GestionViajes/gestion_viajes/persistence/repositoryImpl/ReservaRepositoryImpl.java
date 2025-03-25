package com.GestionViajes.gestion_viajes.persistence.repositoryImpl;

import com.GestionViajes.gestion_viajes.domain.dto.ReservaDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Reserva;
import com.GestionViajes.gestion_viajes.domain.repository.ReservaRepository;
import com.GestionViajes.gestion_viajes.persistence.crud.ReservaCrudRepository;
import com.GestionViajes.gestion_viajes.persistence.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository {

    @Autowired
    private ReservaCrudRepository reservaCrudRepository;

    @Autowired
    private ReservaMapper reservaMapper;

    @Override
    public List<ReservaDTO> findAll() {
        List<Reserva> reservas = (List<Reserva>) reservaCrudRepository.findAll();
        return reservas.stream()
                .map(reservaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservaDTO> findById(Long id) {
        return reservaCrudRepository.findById(id)
                .map(reservaMapper::toDto);
    }

    @Override
    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toEntity(reservaDTO);
        Reserva savedReserva = reservaCrudRepository.save(reserva);
        return reservaMapper.toDto(savedReserva);
    }

    @Override
    public ReservaDTO update(ReservaDTO reservaDTO) {
        if (existsById(reservaDTO.getId())) {
            Reserva reserva = reservaMapper.toEntity(reservaDTO);
            Reserva updatedReserva = reservaCrudRepository.save(reserva);
            return reservaMapper.toDto(updatedReserva);
        }
        throw new IllegalArgumentException("La reserva no existe");
    }

    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            reservaCrudRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("La reserva no existe");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return reservaCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return reservaCrudRepository.count();
    }
}
