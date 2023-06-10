package com.carrentalservice.transformer;

import com.carrentalservice.dto.RevenueDTO;
import com.carrentalservice.entity.Revenue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RevenueTransformer {

    public Revenue transformFromDTOToEntity(RevenueDTO revenueDTO) {
        Revenue revenue = new Revenue();
        BeanUtils.copyProperties(revenueDTO, revenue);
        return revenue;
    }

    public RevenueDTO transformFromEntityToDTO(Revenue revenue) {
        RevenueDTO revenueDTO = new RevenueDTO();
        BeanUtils.copyProperties(revenue, revenueDTO);
        return revenueDTO;

    }
}
