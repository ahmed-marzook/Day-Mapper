package com.kaizenflow.daymapper.mapper;

import com.kaizenflow.daymapper.entities.User;
import com.kaizenflow.daymapper.model.user.UserCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserCreateResponse userToUserCreateResponse(User user);
}
