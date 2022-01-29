package com.example.order.service.dto;

import com.example.order.enums.Gender;

public interface IUser {

    Long getId();

    String getEmail();

    String getPassword();

    String getName();

    String getNickName();

    String getPhoneNumber();

    Gender getGender();

}
