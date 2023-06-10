package com.carrentalservice.transformer;

import com.carrentalservice.dto.RevenueDto;
import com.carrentalservice.entity.Revenue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RevenueTransformer {

    public Revenue transformFromDtoToEntity(RevenueDto revenueDTO) {
        Revenue revenue = new Revenue();
        BeanUtils.copyProperties(revenueDTO, revenue);

        return revenue;
    }

    public RevenueDto transformFromEntityToDto(Revenue revenue) {
        RevenueDto revenueDTO = new RevenueDto();
        BeanUtils.copyProperties(revenue, revenueDTO);

        return revenueDTO;
    }
}
