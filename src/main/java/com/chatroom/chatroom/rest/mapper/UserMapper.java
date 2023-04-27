package com.chatroom.chatroom.rest.mapper;

import com.chatroom.chatroom.domain.User;
import com.chatroom.chatroom.rest.dto.CreateUserDto;
import com.chatroom.chatroom.rest.dto.UpdateUserDto;
import com.chatroom.chatroom.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(
            target = "userChats",
            expression = "java(java.util.Collections.emptySet())"
    )
    User fromCreateUserDto(CreateUserDto userDto);

    User fromUpdateUserDto(UpdateUserDto userDto);

    UserDto toUserDto(User create);

    List<UserDto> toUserDtoList(List<User> users);

}
