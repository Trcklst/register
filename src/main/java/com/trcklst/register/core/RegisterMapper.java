package com.trcklst.register.core;

import com.trcklst.register.core.db.User;
import com.trcklst.register.core.dto.RegisterIn;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    User map(RegisterIn registerIn);
}
