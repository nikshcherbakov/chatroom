package com.chatroom.chatroom.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chatroom.chatroom.TestingUtils;
import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.repository.UserRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl service;

    @Mock
    UserRepository userRepository;

    @Test
    public void givenUserWithNoEmail_whenCreatingUser_thenDoNotCallRepository() {
        User user = TestingUtils.createTestUser(null);
        user.setEmail(null);

        service.create(user);

        verify(userRepository, times(0)).existsByEmail(anyString());
    }

    @Test
    public void givenUserWithExistingEmail_whenCreatingUser_thenThrowException() {
        User user = TestingUtils.createTestUser(null);
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(UserBusinessException.class, () -> service.create(user));
    }

    @Test
    public void givenCorrectUser_whenCreatingUser_thenInvokeRepository() {
        User user = TestingUtils.createTestUser(null);

        service.create(user);

        verify(userRepository, times(1)).save(user);
    }

}
