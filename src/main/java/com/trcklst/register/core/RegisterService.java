package com.trcklst.register.core;

import com.trcklst.register.core.db.Account;
import com.trcklst.register.core.db.AccountRepository;
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
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account process(RegisterIn registerIn) throws UsernameAlreadyExistsException {
        if (isUsernameExists(registerIn))
            throw new UsernameAlreadyExistsException(registerIn.getUsername());
        Account account = createAccount(registerIn);
        return accountRepository.save(account);
    }

    private boolean isUsernameExists(RegisterIn registerIn) {
        return accountRepository.existsByUsername(registerIn.getUsername());
    }

    private Account createAccount(RegisterIn registerIn) {
        Account account = registerMapper.map(registerIn);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setAuthority(AuthoritiesType.ROLE_USER);
        account.setId(getId());
        account.setActive(true);
        return account;
    }

    private Integer getId() {
        Optional<Account> maxIdAccount = accountRepository.findFirstByOrderByIdDesc();
        return maxIdAccount.map(account -> account.getId() + 1).orElse(FIRST_ID);
    }
}
