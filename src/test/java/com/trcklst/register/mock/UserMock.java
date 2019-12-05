package com.trcklst.register.mock;

import com.trcklst.register.core.db.User;
import com.trcklst.register.core.db.AuthoritiesType;

public class UserMock {

    public static final User VALID_USER = getValidUser();

    private static User getValidUser() {
        return User.builder()
                .id(1)
                .username("user")
                .password("password")
                .active(true)
                .authority(AuthoritiesType.ROLE_USER)
                .build();
    }
}
