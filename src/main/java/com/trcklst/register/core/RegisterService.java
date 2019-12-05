package com.trcklst.register.core;

import com.trcklst.register.core.db.User;
import com.trcklst.register.core.db.UserRepository;
import com.trcklst.register.core.db.AuthoritiesType;
import com.trcklst.register.core.dto.RegisterIn;
import com.trcklst.register.core.exceptions.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private static final int FIRST_ID = 1;

    private final RegisterMapper registerMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User process(RegisterIn registerIn) throws UsernameAlreadyExistsException {
        if (isUsernameExists(registerIn))
            throw new UsernameAlreadyExistsException(registerIn.getUsername());
        User user = createUser(registerIn);
        return userRepository.save(user);
    }

    private boolean isUsernameExists(RegisterIn registerIn) {
        return userRepository.existsByUsername(registerIn.getUsername());
    }

    private User createUser(RegisterIn registerIn) {
        User user = registerMapper.map(registerIn);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(AuthoritiesType.ROLE_USER);
        user.setId(getId());
        user.setActive(true);
        return user;
    }

    private Integer getId() {
        Optional<User> maxIdAccount = userRepository.findFirstByOrderByIdDesc();
        return maxIdAccount.map(account -> account.getId() + 1).orElse(FIRST_ID);
    }
}
