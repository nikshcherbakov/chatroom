package com.chatroom.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatroom.chatroom.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

}
