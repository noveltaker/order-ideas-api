package com.example.order.service.userpage;

import com.example.order.repository.UserRepository;
import com.example.order.service.dto.PageDTO;
import org.springframework.data.domain.PageRequest;

public class UserPageFactoryImpl implements UserPageFactory {

    private final UserRepository userRepository;

    private final PageRequest pageRequest;

    private final PageDTO dto;

    public UserPageFactoryImpl(UserRepository userRepository, PageDTO dto) {
        this.userRepository = userRepository;
        this.pageRequest = PageRequest.of(dto.getPage(), dto.getSize());
        this.dto = dto;
    }

    @Override
    public UserPage makeUserPage() {
        switch (dto.getSearchType()) {
            case EMAIL:
                return new EmailUserPage(this.pageRequest, this.dto, this.userRepository);
            case NAME:
                return new NameUserPage(this.pageRequest, this.dto, this.userRepository);
            case EMAIL_NAME:
                return new EmailNameUserPage(this.pageRequest, this.dto, this.userRepository);
            default:
                return new DefaultUserPage(this.pageRequest, this.userRepository);
        }
    }

}
