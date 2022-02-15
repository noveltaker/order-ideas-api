package com.example.order.service.userpage;

import com.example.order.repository.UserRepository;
import com.example.order.service.dto.IPageUser;
import com.example.order.service.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class EmailNameUserPage extends UserPage {

    private final PageRequest pageRequest;

    private final PageDTO dto;

    private final UserRepository userRepository;

    public EmailNameUserPage(PageRequest pageRequest, PageDTO dto, UserRepository userRepository) {
        this.pageRequest = pageRequest;
        this.dto = dto;
        this.userRepository = userRepository;
    }

    @Override
    public Page<IPageUser> getPageList() {
        return userRepository.findAllByNameContainingOrEmailContaining(pageRequest, dto.getSearchValue(), dto.getSearchValue(), IPageUser.class);
    }
}
