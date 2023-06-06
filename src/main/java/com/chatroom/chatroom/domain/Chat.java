package com.chatroom.chatroom.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"userChats", "messages"})
@ToString(exclude = {"userChats", "messages"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    ChatType type;

    @OneToMany(mappedBy = "id.chat")
    Set<UserChat> userChats;

    @OneToMany(mappedBy = "id.chat")
    List<ChatMessage> messages;

}
