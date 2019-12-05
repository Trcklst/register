package com.trcklst.register.mock;

import com.trcklst.register.core.dto.RegisterIn;

public class RegisterInMock {

    public static final RegisterIn REGISTER_IN = createRegisterIn();

    private static RegisterIn createRegisterIn() {
        RegisterIn registerIn = new RegisterIn();
        registerIn.setUsername("user@gmail.com");
        registerIn.setPassword("password");
        return registerIn;
    }
}
