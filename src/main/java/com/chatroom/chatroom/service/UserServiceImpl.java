package com.chatroom.chatroom.service;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.exception.AccessDeniedException;
import com.chatroom.chatroom.exception.NotFoundObjectException;
import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.repository.UserRepository;
import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository repository;

    @Override
    public User create(@Validated(UserCreation.class) User user) {
        String email = user.getEmail();
        ensureEmailNotOccupied(email);
        return repository.save(user);
    }

    @Override
    public User update(@Validated(UserUpdate.class) User user, Long userId) {
        User userToUpdate = findByIdOrThrowException(user.getId());
        checkUserAccess(userId, user.getId());
        User updatedUser = changeUserFields(userToUpdate, user);
        return repository.save(updatedUser);
    }

    @Override
    public User findById(Long userId) {
        return findByIdOrThrowException(userId);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long userToCheckId, Long userId) {
        findByIdOrThrowException(userId);
        checkUserAccess(userToCheckId, userId);
        repository.deleteById(userId);
    }

    private void ensureEmailNotOccupied(String email) {
        if (email != null && repository.existsByEmail(email)) {
            throw new UserBusinessException("Пользователь с указанным email уже существует!");
        }
    }

    private User findByIdOrThrowException(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundObjectException("Объект не был найден"));
    }

    private void checkUserAccess(Long userIdToCheck, Long userIdToChange) {
        if (!userIdToCheck.equals(userIdToChange)) {
            throw new AccessDeniedException("У этого пользователя нет права на эту операцию!");
        }
    }

    private User changeUserFields(User oldUser, User newUser) {
        if (newUser.getEmail() != null && !newUser.getEmail().isBlank()) {

            ensureEmailNotOccupied(newUser.getEmail());
            oldUser.setEmail(newUser.getEmail());
        }
        if (newUser.getFirstName() != null && !newUser.getFirstName().isBlank()) {
            oldUser.setFirstName(newUser.getFirstName());
        }
        if (newUser.getSecondName() != null && !newUser.getSecondName().isBlank()) {
            oldUser.setSecondName(newUser.getSecondName());
        }
        if (newUser.getLastName() != null && !newUser.getLastName().isBlank()) {
            oldUser.setLastName(newUser.getLastName());
        }
        if (newUser.getBirthDate() != null && newUser.getBirthDate().isBefore(LocalDate.now())) {
            oldUser.setBirthDate(newUser.getBirthDate());
        }
        return oldUser;
    }
}
