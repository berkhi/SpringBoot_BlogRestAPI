package com.berkhayta.springbootblogrestapi.controller;

import com.berkhayta.springbootblogrestapi.dto.request.UserSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.UpdateUserListByIdResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListByIdResponseDto;
import com.berkhayta.springbootblogrestapi.entity.User;
import com.berkhayta.springbootblogrestapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.berkhayta.springbootblogrestapi.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT + USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> saveDto(UserSaveRequestDto dto) {
        return ResponseEntity.ok(userService.saveDto(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserListAllResponseDto>> findAllDto() {
        return ResponseEntity.ok(userService.findAllUserDto());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Optional<UserListByIdResponseDto>> findUserById(Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserListByIdResponseDto> updateUser(Long userId, UserSaveRequestDto updatedUserData) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUserData));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}


