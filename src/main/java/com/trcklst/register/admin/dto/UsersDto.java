package com.trcklst.register.admin.dto;

import com.trcklst.register.core.db.AuthoritiesType;
import lombok.Data;

import java.util.List;

@Data
public class UsersDto {

    List<UserDto> users;

    @Data
    public static class UserDto {

        private Integer id;
        private String email;
        private AuthoritiesType authority;
    }
}
