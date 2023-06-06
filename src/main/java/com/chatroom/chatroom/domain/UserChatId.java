package com.chatroom.chatroom.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "create")
public class UserChatId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    Chat chat;

}
