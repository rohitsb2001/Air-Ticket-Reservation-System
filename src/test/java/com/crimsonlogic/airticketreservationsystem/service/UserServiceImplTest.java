package com.crimsonlogic.airticketreservationsystem.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crimsonlogic.airticketreservationsystem.entity.Users;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Users user;

    @BeforeEach
    public void setUp() {
        user = new Users();
        user.setUserId(BigInteger.valueOf(1L));
    }

    @Test
    public void testCreateUserSuccess() {
        given(userRepository.findById(user.getUserId())).willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);

        ResponseEntity<?> result = userService.createUser(user);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateUserAlreadyPresent() {
        given(userRepository.findById(user.getUserId())).willReturn(Optional.of(user));

        ResponseEntity<?> result = userService.createUser(user);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateUser() {
        given(userRepository.findById(user.getUserId())).willReturn(Optional.of(user));
        given(userRepository.save(user)).willReturn(user);

        Users result = userService.updateUser(user);

        assertThat(result).isEqualTo(user);
    }

    @Test
    public void testUpdateUserNotFound() {
        given(userRepository.findById(user.getUserId())).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateUser(user))
            .isInstanceOf(RecordNotFoundException.class)
            .hasMessageContaining("not exists!!");
    }

    @Test
    public void testDeleteUser() {
        BigInteger userId = user.getUserId();
        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        String result = userService.deleteUser(userId);

        assertThat(result).isEqualTo("User Deleted!!");
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUserNotFound() {
        BigInteger userId = user.getUserId();
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteUser(userId))
            .isInstanceOf(RecordNotFoundException.class)
            .hasMessageContaining("not found for the entered UserID");
    }

    @Test
    public void testDisplayAllUser() {
        List<Users> userList = new ArrayList<>();
        userList.add(user);

        given(userRepository.findAll()).willReturn(userList);

        Iterable<Users> result = userService.displayAllUser();

        assertThat(result).isEqualTo(userList);
    }

    @Test
    public void testFindUserByIdSuccess() {
        given(userRepository.findById(user.getUserId())).willReturn(Optional.of(user));

        ResponseEntity<?> result = userService.findUserById(user.getUserId());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(user);
    }

    @Test
    public void testFindUserByIdNotFound() {
        BigInteger userId = user.getUserId();
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        ResponseEntity<?> result = userService.findUserById(userId);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isEqualTo("No record found with ID " + userId);
    }
}

