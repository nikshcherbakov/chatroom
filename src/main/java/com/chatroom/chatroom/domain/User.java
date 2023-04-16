package com.chatroom.chatroom.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;

import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;

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
    @Null(
        groups = UserCreation.class, 
        message = "При создании пользователя запрещено указывать id самостоятельно!"
    )
    @NotNull(
        groups = UserUpdate.class, 
        message = "При обновлении пользователя id является обязательным!"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Имя пользователя является обязательным!")
    @Column(nullable = false)
    String firstName;

    @Column(nullable = true)
    String secondName;

    @Column(nullable = true)
    String lastName;

    @Past
    @Column(nullable = true)
    LocalDate birthDate;

    @Email(message = "Некорректный формат email!")
    @Column(unique = true, nullable = true)
    String email;

    @OneToMany(mappedBy = "id.user")
    Set<UserChat> userChats;

}
