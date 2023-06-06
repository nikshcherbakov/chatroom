package com.chatroom.chatroom.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "create")
public class ChatMessageId implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    Chat chat;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long messageId;

}
