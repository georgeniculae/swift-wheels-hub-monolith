package com.swiftwheelshub.mapper;

import com.swiftwheelshub.dto.UserDto;
import com.swiftwheelshub.entity.User;
import com.swiftwheelshub.util.AssertionUtils;
import com.swiftwheelshub.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private final UserMapper rentalOfficeMapper = new UserMapperImpl();

    @Test
    void mapEntityToDtoTest_success() {
        User user = TestUtils.getResourceAsJson("/data/User.json", User.class);

        UserDto userDto = rentalOfficeMapper.mapEntityToDto(user);

        assertNotNull(userDto);
        AssertionUtils.assertUser(user, userDto);
    }

    @Test
    void mapDtoToEntityTest_success() {
        UserDto userDto = TestUtils.getResourceAsJson("/data/UserDto.json", UserDto.class);

        User user = rentalOfficeMapper.mapDtoToEntity(userDto);

        assertNotNull(user);
        AssertionUtils.assertUser(user, userDto);
    }

}
