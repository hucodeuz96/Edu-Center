package com.example.hucodeuz.component;

import com.example.hucodeuz.entity.Role;
import com.example.hucodeuz.entity.User;
import com.example.hucodeuz.repository.RoleRepository;
import com.example.hucodeuz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author "Husniddin Ulachov"
 * @created 8:32 PM on 7/12/2022
 * @project Edu-Center
 */
@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")){
            Role admin = roleRepository.save(new Role(1l, "ADMIN",true));
            Role user = roleRepository.save(new Role(2l, "USER",true));
            Role mentor = roleRepository.save(new Role(3l, "MENTOR",true));
            Role moderator = roleRepository.save(new Role(4l, "MODERATOR",true));

            Set<Role> roles = new HashSet<>();
            roles.add(admin);
            roles.add(user);


            userRepository.save(new User(roles, "TJU", "admin", passwordEncoder.encode("123"), true));
            userRepository.save(new User(roles, "AAA", "user", passwordEncoder.encode("111"), true));

        }
    }
}
