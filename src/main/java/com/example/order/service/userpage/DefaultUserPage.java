package com.example.order.service.userpage;

import com.example.order.repository.UserRepository;
import com.example.order.service.dto.IPageUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class DefaultUserPage extends UserPage {

    private final PageRequest pageRequest;

    private final UserRepository userRepository;

    public DefaultUserPage(PageRequest pageRequest, UserRepository userRepository) {
        this.pageRequest = pageRequest;
        this.userRepository = userRepository;
    }

    @Override
    public Page<IPageUser> getPageList() {
        return userRepository.findAllProjectedBy(pageRequest, IPageUser.class);
    }
}
