package com.hele.UnitTests;

import com.hele.dto.UserDto;
import com.hele.entity.User;
import com.hele.repository.UserRepository;
import com.hele.service.UserService;
import com.hele.service.UserServiceImpl;
import com.hele.repository.HotelRepository;
import com.hele.utils.Gender;
import com.hele.utils.Role;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HotelRepository hotelRepository;

    private UserService userService;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void beforeEachTest() {
        initMocks(this);

        userService = new UserServiceImpl(userRepository, hotelRepository);
    }

    @Test
    public void testUserRegistration() {
        User user = User.builder()
                .username("owner")
                .firstName("Francesco")
                .lastName("Franco")
                .gender(Gender.MALE)
                .build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<UserDto> listOfUsers = userService.getAllUsers();

        assert (listOfUsers.size() == 1);
        assertNotNull(listOfUsers.get(0));
    }

    @Test
    public void usernameIsNull() {
        User user = User.builder()
                .username("")
                .firstName("Francesco")
                .lastName("Franco")
                .password("password")
                .email("owner@yahoo.com")
                .birthDate(new Date())
                .gender(Gender.MALE)
                .registrationDate(OffsetDateTime.now())
                .role(Role.OWNER)
                .build();

        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "may not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }


}
