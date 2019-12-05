package com.trcklst.register.core.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
public class RegisterIn {

    @NotEmpty(message = "Username can not be empty")
    @Size(min = 3, message = "Username must have at least 3 characters")
    private String username;
    @NotEmpty(message = "Username can not be empty")
    @Size(min = 3, message = "Password must have at least 3 characters")
    private String password;
}
