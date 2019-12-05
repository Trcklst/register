package com.trcklst.register.mock;

import com.trcklst.register.core.dto.RegisterIn;

public class RegisterInMock {

    public static final RegisterIn REGISTER_IN = createRegisterIn();

    private static RegisterIn createRegisterIn() {
        return RegisterIn.builder()
                .username("user@gmail.com")
                .password("password")
                .build();
    }
}
