package com.chatroom.chatroom.service;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.exception.NotFoundObjectException;
import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.repository.UserRepository;
import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
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
    public User update(@Validated(UserUpdate.class) User user) {
        findByIdOrThrowException(user.getId());
        ensureEmailNotOccupied(user.getEmail());
        return repository.save(user);
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
    public void deleteById(Long userId) {
        findByIdOrThrowException(userId);
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

    private User updateUserFields(User existingUser, User updatedUser) {
        if (StringUtils.isNotBlank(updatedUser.getEmail())) {

            ensureEmailNotOccupied(updatedUser.getEmail());
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (StringUtils.isNotBlank(updatedUser.getFirstName())) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }
        if (StringUtils.isNotBlank(updatedUser.getSecondName())) {
            existingUser.setSecondName(updatedUser.getSecondName());
        }
        if (StringUtils.isNotBlank(updatedUser.getLastName())) {
            existingUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getBirthDate() != null && updatedUser.getBirthDate().isBefore(LocalDate.now())) {
            existingUser.setBirthDate(updatedUser.getBirthDate());
        }
        return existingUser;
    }
}
