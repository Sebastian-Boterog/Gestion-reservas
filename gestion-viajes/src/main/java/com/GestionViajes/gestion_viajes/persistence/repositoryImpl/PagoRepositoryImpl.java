package com.GestionViajes.gestion_viajes.persistence.repositoryImpl;

import com.GestionViajes.gestion_viajes.domain.dto.PagoDTO;
import com.GestionViajes.gestion_viajes.domain.entity.Pago;
import com.GestionViajes.gestion_viajes.domain.repository.PagoRepository;
import com.GestionViajes.gestion_viajes.persistence.crud.PagoCrudRepository;
import com.GestionViajes.gestion_viajes.persistence.mapper.PagoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PagoRepositoryImpl implements PagoRepository {

    private final PagoCrudRepository pagoCrudRepository;
    private final PagoMapper pagoMapper;

    public PagoRepositoryImpl(PagoCrudRepository pagoCrudRepository, PagoMapper pagoMapper) {
        this.pagoCrudRepository = pagoCrudRepository;
        this.pagoMapper = pagoMapper;
    }

    @Override
    public List<PagoDTO> findAll() {
        List<Pago> pagos = (List<Pago>) pagoCrudRepository.findAll();
        return pagos.stream()
                .map(pagoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PagoDTO> findById(Long id) {
        return pagoCrudRepository.findById(id).map(pagoMapper::toDto);
    }

    @Override
    public PagoDTO save(PagoDTO pagoDTO) {
        Pago pago = pagoMapper.toEntity(pagoDTO);
        return pagoMapper.toDto(pagoCrudRepository.save(pago));
    }

    @Override
    public PagoDTO update(PagoDTO pagoDTO) {
        if (pagoCrudRepository.existsById(pagoDTO.getId())) {
            Pago pago = pagoMapper.toEntity(pagoDTO);
            return pagoMapper.toDto(pagoCrudRepository.save(pago));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        pagoCrudRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return pagoCrudRepository.existsById(id);
    }
}
