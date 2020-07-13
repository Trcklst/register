package com.trcklst.register.admin;

import com.trcklst.register.admin.dto.UsersDto;
import com.trcklst.register.core.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "email", source = "username")
    UsersDto.UserDto mapUser(User user);
}
