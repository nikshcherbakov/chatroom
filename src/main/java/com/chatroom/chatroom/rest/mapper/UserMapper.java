package com.chatroom.chatroom.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UserDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(
        target = "userChats", 
        expression = "java(java.util.Collections.emptySet())"
    )
    User fromCreateUserDto(CreateUserDto userDto);

    UserDto toUserDto(User create);

}
