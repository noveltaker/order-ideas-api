package com.example.order.service;

import com.example.order.config.security.SecurityConstants;
import com.example.order.domain.Authority;
import com.example.order.domain.User;
import com.example.order.repository.UserRepository;
import com.example.order.service.dto.IPageUser;
import com.example.order.service.dto.PageDTO;
import com.example.order.service.dto.UserDTO;
import com.example.order.service.userpage.UserPage;
import com.example.order.service.userpage.UserPageFactory;
import com.example.order.service.userpage.UserPageFactoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public User joinUser(UserDTO dto) {
        User entity = dto.toEntity();
        entity.setNewUser(passwordEncoder.encode(dto.getPassword()), getAuthorities());
        userRepository.save(entity);
        return entity;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(User.builder().build());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Page<IPageUser> getUserList(PageDTO dto) {

        UserPageFactory userPageFactory = new UserPageFactoryImpl(userRepository, dto);

        UserPage userPage = userPageFactory.makeUserPage();

        return userPage.getPageList();
    }

    private Set<Authority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.builder().name(SecurityConstants.ROLE_USER).build());
        return authorities;
    }
}
