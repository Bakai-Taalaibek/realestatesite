package com.company.realestatesite.services.impl;

import com.company.realestatesite.mappers.UserMapper;
import com.company.realestatesite.models.dtos.UserDto;
import com.company.realestatesite.models.entities.Role;
import com.company.realestatesite.models.entities.User;
import com.company.realestatesite.models.enums.Status;
import com.company.realestatesite.repository.RoleRepository;
import com.company.realestatesite.repository.UserRepository;
import com.company.realestatesite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto register(UserDto userDto) {
        return userMapper.toDto(userRepository.save(toEntity(userDto)));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User is not found"));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto update(UserDto dto) {
        try {
        User user = userRepository.findByUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setGender(dto.getGender());
        user.setYearOfBirth(dto.getYearOfBirth());
        return userMapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }

    private User toEntity(UserDto userDto) {


        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setYearOfBirth(userDto.getYearOfBirth());
        user.setGender(userDto.getGender());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        return user;
    }
}
