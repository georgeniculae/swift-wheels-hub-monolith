package com.swiftwheelshub.service;

import com.swiftwheelshub.dto.RentalOfficeRequest;
import com.swiftwheelshub.dto.RentalOfficeResponse;
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

    public RentalOfficeResponse saveRentalOffice(RentalOfficeRequest rentalOfficeRequest) {
        RentalOffice rentalOffice = rentalOfficeMapper.mapDtoToEntity(rentalOfficeRequest);
        RentalOffice savedRentalOffice = rentalOfficeRepository.save(rentalOffice);

        return rentalOfficeMapper.mapEntityToDto(savedRentalOffice);
    }

    public List<RentalOfficeResponse> findAllRentalOffices() {
        return rentalOfficeRepository.findAll()
                .stream()
                .map(rentalOfficeMapper::mapEntityToDto)
                .toList();
    }

    public void deleteRentalOfficeById(Long id) {
        rentalOfficeRepository.deleteById(id);
    }

    public RentalOfficeResponse findRentalOfficeById(Long id) {
        RentalOffice rentalOffice = findEntityById(id);

        return rentalOfficeMapper.mapEntityToDto(rentalOffice);
    }

    public RentalOffice findEntityById(Long id) {
        return rentalOfficeRepository.findById(id)
                .orElseThrow(() -> new SwiftWheelsHubNotFoundException("Rental office with id " + id + " does not exist"));
    }

    public RentalOfficeResponse updateRentalOffice(Long id, RentalOfficeRequest updatedRentalOfficeRequest) {
        RentalOffice existingRentalOffice = findEntityById(id);

        existingRentalOffice.setName(updatedRentalOfficeRequest.getName());
        existingRentalOffice.setContactAddress(updatedRentalOfficeRequest.getContactAddress());
        existingRentalOffice.setPhoneNumber(updatedRentalOfficeRequest.getPhoneNumber());

        RentalOffice savedRentalOffice = rentalOfficeRepository.save(existingRentalOffice);

        return rentalOfficeMapper.mapEntityToDto(savedRentalOffice);
    }

    public Long countRentalOffices() {
        return rentalOfficeRepository.count();
    }

    public RentalOfficeResponse findRentalOfficeByName(String searchString) {
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
