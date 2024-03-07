package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RentalOfficeDto;
import com.swiftwheelshub.entity.RentalOffice;
import com.swiftwheelshub.exception.SwiftWheelsHubNotFoundException;
import com.swiftwheelshub.mapper.RentalOfficeMapper;
import com.swiftwheelshub.repository.RentalOfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public RentalOffice findEntityById(Long id) {
        return rentalOfficeRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Rental office with id " + id + " does not exist"));
    }

    public RentalOfficeDto updateRentalOffice(Long id, RentalOfficeDto updatedRentalOfficeDto) {
        Long actualId = getId(id, updatedRentalOfficeDto.getId());

        RentalOffice existingRentalOffice = findEntityById(actualId);

        existingRentalOffice.setName(updatedRentalOfficeDto.getName());
        existingRentalOffice.setContactAddress(updatedRentalOfficeDto.getContactAddress());
        existingRentalOffice.setPhoneNumber(updatedRentalOfficeDto.getPhoneNumber());

        RentalOffice savedRentalOffice = rentalOfficeRepository.save(existingRentalOffice);

        return rentalOfficeMapper.mapEntityToDto(savedRentalOffice);
    }

    public Long countRentalOffices() {
        return rentalOfficeRepository.count();
    }

    public RentalOfficeDto findRentalOfficeByName(String searchString) {
        return rentalOfficeRepository.findRentalOfficeByName(searchString)
                .map(rentalOfficeMapper::mapEntityToDto)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Rental office with name: " + searchString + " does not exist"));
    }

    private Long getId(Long id, Long updatedRentalOfficeId) {
        Long actualId = updatedRentalOfficeId;

        if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(id)) {
            actualId = id;
        }

        return actualId;
    }

}
