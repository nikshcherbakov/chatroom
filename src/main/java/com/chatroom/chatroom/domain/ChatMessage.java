package com.chatroom.chatroom.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    @EmbeddedId
    ChatMessageId id;

    @Column(nullable = false)
    Instant time;

    @Column(nullable = false)
    String content;

}
