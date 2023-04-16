package com.chatroom.chatroom.validation;

import static java.time.temporal.ChronoUnit.YEARS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.chatroom.chatroom.TestingUtils;
import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserValidationTest {

    Validator validator;
    
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void givenUserWithId_whenCreatingUser_thenReturnConstraintViolation() {
        User user = TestingUtils.createTestUser(1L);

        Set<ConstraintViolation<User>> result = validator.validate(user, UserCreation.class);

        assertEquals(1, result.size());
    }

    @Test
    public void givenUserWithNoId_whenUpdatingUser_thenReturnConstraintViolation() {
        User user = TestingUtils.createTestUser(null);

        Set<ConstraintViolation<User>> result = validator.validate(user, UserUpdate.class);

        assertEquals(1, result.size());
    }

    @Test
    public void givenUserWithoutFirstName_whenValidating_thenReturnConstraintViolation() {
        User user = TestingUtils.createTestUser(1L);
        user.setFirstName(null);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        assertEquals(1, result.size());
    }

    @Test
    public void givenUserWithIncorrectBirthDate_whenValidating_thenReturnConstraintViolation() {
        User user = TestingUtils.createTestUser(1L);
        user.setBirthDate(LocalDate.now().plus(1, YEARS));

        Set<ConstraintViolation<User>> result = validator.validate(user);

        assertEquals(1, result.size());
    }

    @Test
    public void givenUserWithIncorrectEmail_whenValidating_thenReturnConstraintViolation() {
        User user = TestingUtils.createTestUser(1L);
        user.setEmail("incorrectEmailFormat");

        Set<ConstraintViolation<User>> result = validator.validate(user);

        assertEquals(1, result.size());
    }

    @Test
    public void givenValid_whenValidating_thenReturnNoConstraintViolations() {
        User user = TestingUtils.createTestUser(1L);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        assertTrue(result.isEmpty());
    }

}
