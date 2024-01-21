package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.UserDto;
import com.swiftwheelshub.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto mapEntityToDto(User user);

    User mapDtoToEntity(UserDto userDto);

}
