package com.carrentalservice.service;

import com.carrentalservice.dto.RentalOfficeDto;
import com.carrentalservice.entity.RentalOffice;
import com.carrentalservice.mapper.RentalOfficeMapper;
import com.carrentalservice.mapper.RentalOfficeMapperImpl;
import com.carrentalservice.repository.RentalOfficeRepository;
import com.carrentalservice.util.AssertionUtils;
import com.carrentalservice.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

}
