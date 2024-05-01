package com.berkhayta.springbootblogrestapi.service;

import com.berkhayta.springbootblogrestapi.dto.request.UserSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.UpdateUserListByIdResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListByIdResponseDto;
import com.berkhayta.springbootblogrestapi.entity.User;
import com.berkhayta.springbootblogrestapi.exceptions.BlogAppException;
import com.berkhayta.springbootblogrestapi.exceptions.ErrorType;
import com.berkhayta.springbootblogrestapi.mapper.UserMapper;
import com.berkhayta.springbootblogrestapi.repository.UserRepository;
import com.berkhayta.springbootblogrestapi.utility.ServiceManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public User saveDto(UserSaveRequestDto dto) {
        return userRepository.save(UserMapper.INSTANCE.userSaveRequestDtoToUser(dto));
    }

    public List<UserListAllResponseDto> findAllUserDto() {
        List<UserListAllResponseDto> dtoList = new ArrayList<>();
        findAll().forEach(user -> {
            dtoList.add(UserMapper.INSTANCE.userFindAllResponseDto(user));
        });
        return dtoList;
    }

    public Optional<UserListByIdResponseDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::userFindByIdResponseDto);
    }

    public UpdateUserListByIdResponseDto updateUser(Long userId, UserSaveRequestDto updatedUserData) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new BlogAppException(ErrorType.USER_NOT_FOUND, userId + " ID'li kullanıcı bulunamadı."));

        existingUser.setFirstName(updatedUserData.firstName());
        existingUser.setLastName(updatedUserData.lastName());
        existingUser.setEmail(updatedUserData.email());
        existingUser.setPassword(updatedUserData.password());

        userRepository.save(existingUser);
        return UserMapper.INSTANCE.updateUserFindByIdResponseDto(existingUser);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
