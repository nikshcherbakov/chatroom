package com.chatroom.chatroom.domain;

import com.chatroom.chatroom.validation.user.UserCreation;
import com.chatroom.chatroom.validation.user.UserUpdate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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

    String secondName;

    String lastName;

    @Past
    LocalDate birthDate;

    @Email(message = "Некорректный формат email!")
    @Column(unique = true)
    String email;

    @OneToMany(mappedBy = "id.user")
    Set<UserChat> userChats;

}
