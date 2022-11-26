package com.maison.demo.services;

import com.maison.demo.constants.Constant;
import com.maison.demo.models.Role;
import com.maison.demo.models.User;
import com.maison.demo.repositories.RoleRepository;
import com.maison.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User find(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception(String.format("ID [%s] Not Found.", id));
        }
        return user.get();
    }

    public User findByEmail(String email) throws Exception {
        User user = userRepository.readByEmail(email);
        if (user == null) {
            throw new Exception(String.format("Email [%s] Not Exists.", email));
        }
        return user;
    }

    public boolean create(User user) {
        Role role = roleRepository.getByRoleName(Constant.STUDENT_ROLE);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return savedUser.getUserId() > 0;
    }
}
