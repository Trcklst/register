package com.trcklst.register.core;

import com.trcklst.register.core.db.Account;
import com.trcklst.register.core.dto.RegisterIn;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    Account map(RegisterIn registerIn);
}
