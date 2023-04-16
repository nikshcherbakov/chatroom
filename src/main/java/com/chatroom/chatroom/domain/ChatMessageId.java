package com.chatroom.chatroom.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
