package com.chatroom.chatroom.domain;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = true)
    String firstName;

    @Column(nullable = true)
    String secondName;

    @Column(nullable = true)
    String lastName;

    @Column(nullable = true)
    LocalTime birthDate;

    @Column(unique = true, nullable = true)
    String email;

    @OneToMany(mappedBy = "id.user")
    Set<UserChat> userChats;

}
