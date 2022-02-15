package com.example.order.service.userpage;

import com.example.order.service.dto.IPageUser;
import org.springframework.data.domain.Page;

public abstract class UserPage {
    public abstract Page<IPageUser> getPageList();
}
