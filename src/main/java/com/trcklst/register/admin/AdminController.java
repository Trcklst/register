package com.trcklst.register.admin;

import com.trcklst.register.admin.dto.UsersDto;
import com.trcklst.register.core.db.AuthoritiesType;
import com.trcklst.register.core.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final AdminMapper adminMapper;

    @GetMapping("/users/")
    public UsersDto users() {
        List<UsersDto.UserDto> users = userRepository.findByAuthority(AuthoritiesType.ROLE_USER).stream()
                .map(adminMapper::mapUser)
                .collect(Collectors.toList());
        UsersDto usersDto = new UsersDto();
        usersDto.setUsers(users);
        return usersDto;
    }

    @DeleteMapping("/user/{id}/")
    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

}
