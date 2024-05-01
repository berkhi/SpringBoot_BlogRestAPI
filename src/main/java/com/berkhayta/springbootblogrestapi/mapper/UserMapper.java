package com.berkhayta.springbootblogrestapi.mapper;

import com.berkhayta.springbootblogrestapi.dto.request.UserSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.UpdateUserListByIdResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListByIdResponseDto;
import com.berkhayta.springbootblogrestapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapper.class);

    //UserSave
    User userSaveRequestDtoToUser(UserSaveRequestDto userDto);

    //UserListAll
    UserListAllResponseDto userFindAllResponseDto(User user);

    //UserListById
    UserListByIdResponseDto userFindByIdResponseDto(User user);

    UpdateUserListByIdResponseDto updateUserFindByIdResponseDto(User user);
}
