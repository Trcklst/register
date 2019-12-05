package com.trcklst.register.core;

import com.trcklst.register.core.db.Account;
import com.trcklst.register.core.db.AccountRepository;
import com.trcklst.register.core.db.AuthoritiesType;
import com.trcklst.register.core.dto.RegisterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterMapper registerMapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account process(RegisterIn registerIn) {
        Account account = registerMapper.map(registerIn);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setAuthority(AuthoritiesType.ROLE_USER);
        account.setId(getId());
        account.setActive(true);
        return accountRepository.save(account);
    }

    private Integer getId() {
        Optional<Account> maxIdAccount = accountRepository.findFirstByOrderByIdDesc();
        return maxIdAccount.map(account -> account.getId() + 1).orElse(0);
    }
}
