package com.chatroom.chatroom.domain;

import java.time.Instant;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChat {

    @EmbeddedId
    UserChatId id;

    Instant joinTime;

    boolean blocked;

    boolean muted;

    boolean favourite;

}
