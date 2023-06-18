package com.carrentalservice.service;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.RentalOfficeMapper;
import com.carrentalservice.repository.RentalOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalOfficeService {

    private final RentalOfficeRepository rentalOfficeRepository;
    private final RentalOfficeMapper rentalOfficeMapper;

    public RentalOfficeDto saveRentalOffice(RentalOfficeDto rentalOfficeDto) {
        RentalOffice rentalOffice = rentalOfficeMapper.mapDtoToEntity(rentalOfficeDto);
        RentalOffice savedRentalOffice = rentalOfficeRepository.save(rentalOffice);

        return rentalOfficeMapper.mapEntityToDto(savedRentalOffice);
    }

    public List<RentalOfficeDto> findAllRentalOffices() {
        return rentalOfficeRepository.findAll()
                .stream()
                .map(rentalOfficeMapper::mapEntityToDto)
                .toList();
    }

    public void deleteRentalOfficeById(Long id) {
        rentalOfficeRepository.deleteById(id);
    }

    public RentalOfficeDto findRentalOfficeById(Long id) {
        RentalOffice rentalOffice = findEntityById(id);

        return rentalOfficeMapper.mapEntityToDto(rentalOffice);
    }

    public RentalOffice saveEntity(RentalOffice rentalOffice) {
        return rentalOfficeRepository.save(rentalOffice);
    }

    public RentalOffice findEntityById(Long id) {
        Optional<RentalOffice> optionalRentalOffice = rentalOfficeRepository.findById(id);

        if (optionalRentalOffice.isPresent()) {
            return optionalRentalOffice.get();
        }

        throw new NotFoundException("Rental office with id " + id + " does not exist");
    }

    public RentalOfficeDto updateRentalOffice(RentalOfficeDto newRentalOfficeDto) {
        RentalOffice newRentalOffice = rentalOfficeMapper.mapDtoToEntity(newRentalOfficeDto);

        RentalOffice existingRentalOffice = findEntityById(newRentalOfficeDto.getId());

        existingRentalOffice.setName(newRentalOffice.getName());
        existingRentalOffice.setInternetDomain(newRentalOffice.getInternetDomain());
        existingRentalOffice.setContactAddress(newRentalOffice.getContactAddress());
        existingRentalOffice.setLogoType(newRentalOffice.getLogoType());

        RentalOffice savedRentalOffice = rentalOfficeRepository.save(existingRentalOffice);

        return rentalOfficeMapper.mapEntityToDto(savedRentalOffice);
    }

    public Long countRentalOffices() {
        return rentalOfficeRepository.count();
    }

    public RentalOfficeDto findRentalOfficeByName(String searchString) {
        RentalOffice rentalOffice = rentalOfficeRepository.findRentalOfficeByName(searchString);

        return rentalOfficeMapper.mapEntityToDto(rentalOffice);
    }

}
