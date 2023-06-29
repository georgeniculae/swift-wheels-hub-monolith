package com.carrentalservice.service;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.exception.NotFoundException;
import com.carrentalservice.mapper.RentalOfficeMapper;
import com.carrentalservice.mapper.RentalOfficeMapperImpl;
import com.carrentalservice.repository.RentalOfficeRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalOfficeServiceTest {

    @InjectMocks
    private RentalOfficeService rentalOfficeService;

    @Mock
    private RentalOfficeRepository rentalOfficeRepository;

    @Spy
    private RentalOfficeMapper rentalOfficeMapper = new RentalOfficeMapperImpl();

    @Captor
    private ArgumentCaptor<RentalOffice> argumentCaptor = ArgumentCaptor.forClass(RentalOffice.class);

    @Test
    void saveRentalOfficeTest_success() {
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);
        RentalOfficeDto rentalOfficeDto = TestUtils.getResourceAsJson("/data/RentalOfficeDto.json", RentalOfficeDto.class);

        when(rentalOfficeRepository.save(any(RentalOffice.class))).thenReturn(rentalOffice);

        assertDoesNotThrow(() -> rentalOfficeService.saveRentalOffice(rentalOfficeDto));
        RentalOfficeDto savedRentalOfficeDto = rentalOfficeService.saveRentalOffice(rentalOfficeDto);

        AssertionUtils.assertRentalOffice(rentalOffice, savedRentalOfficeDto);

        verify(rentalOfficeRepository, times(2)).save(argumentCaptor.capture());
        verify(rentalOfficeMapper, times(2)).mapEntityToDto(any(RentalOffice.class));
    }

    @Test
    void findAllRentalOfficesTest_success() {
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);

        when(rentalOfficeRepository.findAll()).thenReturn(List.of(rentalOffice));

        assertDoesNotThrow(() -> rentalOfficeService.findAllRentalOffices());
        List<RentalOfficeDto> rentalOfficeDtoList = rentalOfficeService.findAllRentalOffices();

        AssertionUtils.assertRentalOffice(rentalOffice, rentalOfficeDtoList.get(0));
    }

    @Test
    void findRentalOfficeByIdTest_success() {
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);

        when(rentalOfficeRepository.findById(anyLong())).thenReturn(Optional.of(rentalOffice));

        assertDoesNotThrow(() -> rentalOfficeService.findRentalOfficeById(1L));
        RentalOfficeDto rentalOfficeDto = rentalOfficeService.findRentalOfficeById(1L);

        AssertionUtils.assertRentalOffice(rentalOffice, rentalOfficeDto);
    }

    @Test
    void findRentalOfficeByIdTest_errorOnFindingById() {
        when(rentalOfficeRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> rentalOfficeService.findRentalOfficeById(1L));

        assertNotNull(notFoundException);
        assertEquals("Rental office with id 1 does not exist", notFoundException.getMessage());
    }

    @Test
    void updateRentalOfficeTest_success() {
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);
        RentalOfficeDto rentalOfficeDto = TestUtils.getResourceAsJson("/data/RentalOfficeDto.json", RentalOfficeDto.class);

        when(rentalOfficeRepository.findById(anyLong())).thenReturn(Optional.of(rentalOffice));
        when(rentalOfficeRepository.save(any(RentalOffice.class))).thenReturn(rentalOffice);

        assertDoesNotThrow(() -> rentalOfficeService.updateRentalOffice(rentalOfficeDto));
        RentalOfficeDto updatedRentalOfficeDto = rentalOfficeService.updateRentalOffice(rentalOfficeDto);

        AssertionUtils.assertRentalOffice(rentalOffice, updatedRentalOfficeDto);
    }

    @Test
    void findRentalOfficeByNameTest_success() {
        RentalOffice rentalOffice = TestUtils.getResourceAsJson("/data/RentalOffice.json", RentalOffice.class);

        when(rentalOfficeRepository.findRentalOfficeByName(anyString())).thenReturn(Optional.of(rentalOffice));

        assertDoesNotThrow(() -> rentalOfficeService.findRentalOfficeByName("Test Rental Office"));
        RentalOfficeDto rentalOfficeDto = rentalOfficeService.findRentalOfficeByName("Test Rental Office");

        AssertionUtils.assertRentalOffice(rentalOffice, rentalOfficeDto);
    }

    @Test
    void findRentalOfficeByNameTest_errorOnFindingByName() {
        when(rentalOfficeRepository.findRentalOfficeByName(anyString())).thenReturn(Optional.empty());

        NotFoundException notFoundException =
                assertThrows(NotFoundException.class, () -> rentalOfficeService.findRentalOfficeByName("Test Rental Office"));

        assertNotNull(notFoundException);
        assertEquals("Rental office with name: Test Rental Office does not exist", notFoundException.getMessage());
    }

}
