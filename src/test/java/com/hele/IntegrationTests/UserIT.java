package com.hele.IntegrationTests;

import com.hele.entity.User;
import com.hele.repository.UserRepository;
import com.hele.utils.Gender;
import com.hele.utils.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_register_user() {
        User user = userRepository.save(User.builder()
                .id((long) 1)
                .username("owner")
                .password("password")
                .firstName("Francesco")
                .lastName("Franco")
                .email("owner@yahoo.com")
                .birthDate(new Date())
                .gender(Gender.MALE)
                .registrationDate(OffsetDateTime.now())
                .role(Role.OWNER)
                .build());

        User foundUser = userRepository.findOne(user.getId());

        assertNotNull(foundUser);
        assertEquals(user.getFirstName(), foundUser.getFirstName());
    }
}