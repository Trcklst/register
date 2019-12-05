package com.trcklst.register.core;

import com.trcklst.register.core.db.Account;
import com.trcklst.register.core.dto.RegisterIn;
import com.trcklst.register.core.exceptions.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public Account register(@Valid @RequestBody RegisterIn registerIn) throws UsernameAlreadyExistsException {
        return registerService.process(registerIn);
    }
}
