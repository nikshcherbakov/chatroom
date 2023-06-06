package com.chatroom.chatroom.service;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.exception.NotFoundObjectException;
import com.chatroom.chatroom.exception.user.UserBusinessException;
import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {

    User create(@Validated(UserCreation.class) User user);

    /**
     * Обновляет существующего пользователя
     *
     * @param user Обновляемый пользователь
     * @return Обновленный пользователь
     * @throws NotFoundObjectException если пользователь не найден
     * @throws UserBusinessException   если email занят
     */
    User update(@Validated(UserUpdate.class) User user);

    /**
     * Ищет пользователя по id
     *
     * @param userId id пользователя
     * @return Найденный пользователь
     * @throws NotFoundObjectException если пользователь не найден
     */
    User findById(Long userId);

    /**
     * Ищет всех существующих пользователей
     *
     * @return Список существующих пользователей
     */
    List<User> findAll();

    /**
     * Удаляет пользователя по id
     *
     * @param userId id пользователя
     * @throws NotFoundObjectException если пользователь не найден
     */
    void deleteById(Long userId);

}
