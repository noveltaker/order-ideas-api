package com.example.order.repository;

import com.example.order.domain.User;
import com.example.order.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 저장 로직")
    void saveUser() throws Exception {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "0100000000";

        Gender gender = Gender.M;

        User entity = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .build();

        userRepository.save(entity);

        // user pk value null checked
        Assertions.assertNotNull(entity.getId());

        // user email value checked
        Assertions.assertEquals(email, entity.getEmail());

        // 비밀번호 체크
        Assertions.assertEquals(password, entity.getPassword());

        // 이름 체크
        Assertions.assertEquals(name, entity.getName());

        // 닉네임 체크
        Assertions.assertEquals(nickName, entity.getNickName());

        // 전화번호 체크
        Assertions.assertEquals(phoneNumber, entity.getPhoneNumber());

        // 성별 체크
        Assertions.assertEquals(gender, entity.getGender());

    }

}
