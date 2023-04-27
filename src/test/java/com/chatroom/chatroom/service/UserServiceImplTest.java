package com.chatroom.chatroom.service;

import com.chatroom.chatroom.TestingUtils;
import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.exception.AccessDeniedException;
import com.chatroom.chatroom.exception.NotFoundObjectException;
import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static com.chatroom.chatroom.TestingUtils.dateInPast;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void givenNotExistingUser_whenUpdatingUser_thenThrowNotFoundObjectException() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenThrow(NotFoundObjectException.class);

        assertThrows(NotFoundObjectException.class, () -> service.update(user, userId));
    }

    @Test
    public void givenUserIdWithNoAccess_whenUpdatingUser_thenThrowAccessDeniedException() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(AccessDeniedException.class, () -> service.update(user, 2L));
    }

    @Test
    public void givenUserWithOccupiedEmail_whenUpdatingUser_thenThrowUserBusinessException() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(UserBusinessException.class, () -> service.update(user, 1L));
    }

    @Test
    public void givenUserWithNewEmail_whenUpdatingUser_thenReturnUserWithUpdatedEmail() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setEmail("newEmail@mail.ru");
        when(userRepository.save(any())).thenReturn(user);

        assertEquals("newEmail@mail.ru", service.update(user, userId).getEmail());
    }

    @Test
    public void givenUserWithNewFirstName_whenUpdatingUser_thenReturnUserWithUpdatedFirstName() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setFirstName("newFirstName");
        when(userRepository.save(any())).thenReturn(user);

        assertEquals("newFirstName", service.update(user, userId).getFirstName());
    }

    @Test
    public void givenUserWithNewSecondName_whenUpdatingUser_thenReturnUserWithUpdatedSecondName() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setSecondName("newSecondName");
        when(userRepository.save(any())).thenReturn(user);

        assertEquals("newSecondName", service.update(user, userId).getSecondName());
    }

    @Test
    public void givenUserWithNewLastName_whenUpdatingUser_thenReturnUserWithUpdatedLastName() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setLastName("newLastName");
        when(userRepository.save(any())).thenReturn(user);

        assertEquals("newLastName", service.update(user, userId).getLastName());
    }

    @Test
    public void givenUserWithNewBirthdate_whenUpdatingUser_thenReturnUserWithUpdatedBirthDate() {
        Long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        LocalDate newBirthDate = dateInPast();
        user.setBirthDate(newBirthDate);
        when(userRepository.save(any())).thenReturn(user);

        assertEquals(newBirthDate, service.update(user, userId).getBirthDate());
    }

    @Test
    public void givenNotExistingUserId_whenFindingById_thenThrowNotFoundObjectException() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenThrow(NotFoundObjectException.class);

        assertThrows(NotFoundObjectException.class, () -> service.findById(userId));
    }

    @Test
    public void givenExistingUserId_whenFindingById_thenReturnUser() {
        long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertEquals(user, service.findById(userId));
    }

    @Test
    public void givenUsers_whenFindingAllUsers_thenInvokeRepository() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        service.findAll();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void givenNotExistingUserId_whenDeletingById_throwNotFoundObjectException() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenThrow(NotFoundObjectException.class);

        assertThrows(NotFoundObjectException.class, () -> service.deleteById(1L, userId));
    }

    @Test
    public void givenNoAccessUserId_whenDeletingById_throwAccessDeniedException() {
        long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(AccessDeniedException.class, () -> service.deleteById(2L, userId));
    }

    @Test
    public void givenCorrectUserId_whenDeletingById_thenInvokeRepository() {
        long userId = 1L;
        User user = TestingUtils.createTestUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        service.deleteById(1L, userId);

        verify(userRepository, times(1)).deleteById(1L);
    }

}
