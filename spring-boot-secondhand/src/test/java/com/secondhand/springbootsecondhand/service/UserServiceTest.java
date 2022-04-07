package com.secondhand.springbootsecondhand.service;

import com.secondhand.springbootsecondhand.TestSupport;
import com.secondhand.springbootsecondhand.dto.CreateUserRequest;
import com.secondhand.springbootsecondhand.dto.UpdateUserRequest;
import com.secondhand.springbootsecondhand.dto.UserDto;
import com.secondhand.springbootsecondhand.dto.UserDtoConvertor;
import com.secondhand.springbootsecondhand.exception.UserIsNotActiveException;
import com.secondhand.springbootsecondhand.exception.UserNotFoundException;
import com.secondhand.springbootsecondhand.model.User;
import com.secondhand.springbootsecondhand.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

public class UserServiceTest extends TestSupport {

    private UserDtoConvertor convertor;
    private UserRepository repository;

    private UserService userService;

    @BeforeEach   //in every test case , this method runs
    public void setUp(){
        convertor = Mockito.mock(UserDtoConvertor.class);
        repository = Mockito.mock(UserRepository.class);

        userService = new UserService(repository, convertor);
    }

    @Test
    public void testGetAllUsers_ItShouldReturnUsersDtoList(){
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);
        when(repository.findAll()).thenReturn(userList);
        when(convertor.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUser();

        assertEquals(userDtoList, result);

        verify(repository).findAll();//is it really colled that findAll() method
        verify(convertor).convert(userList);

    }

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUsersDto(){
        String mail = "mail@gmail.com";
        User user = generateUser(mail);
        UserDto userDto = generateUserDto(mail);

        when(repository.findByEmail(mail)).thenReturn(Optional.of(user));
        when(convertor.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserByMail(mail);

        assertEquals(userDto, result);

        verify(repository).findByEmail(mail);//is it really colled that findAll() method
        verify(convertor).convert(user);

    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "mail@gmail.com";

        when(repository.findByEmail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserByMail(mail));

        verify(repository).findByEmail(mail);
        verifyNoInteractions(convertor);

    }

    @Test
    public void testCreateUser_itShouldReturnCreatedUserDto(){
        String mail = "mail@gmail.com";
        CreateUserRequest  request = new CreateUserRequest(mail, "firstName", "lastName");
        User user = new User(mail,
                "firstName",
                "lastName",
                false);

        User savedUser = new User(1L, mail,
                        "firstName","lastName",
                        false);

        UserDto userDto = new UserDto(mail,
                            "firstName","lastName");


        when(repository.save(user)).thenReturn(savedUser);
        when(convertor.convert(savedUser)).thenReturn(userDto);

        UserDto result =  userService.createUser(request);

        assertEquals(userDto, result);

        verify(repository).save(user);
        verify(convertor).convert(savedUser);

    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto(){
        String mail = "mail@gmail.com";

        UpdateUserRequest request = new UpdateUserRequest("firstName2", "lastName2", "middleName2");
        User user = new User(1L, mail, "firstName", "lastName", true);
        User updatedUser = new User(1L, mail, "firstName2", "lastName2", true);
        User savedUser = new User(1L, mail, "firstName2","lastName2", true);
        UserDto userDto = new UserDto(mail, "firstName2","lastName2");

        when(repository.findByEmail(mail)).thenReturn(Optional.of(user));
        when(repository.save(updatedUser)).thenReturn(savedUser);
        when(convertor.convert(savedUser)).thenReturn(userDto);

        UserDto result =  userService.updateUser(mail, request);

        assertEquals(userDto, result);

        verify(repository).findByEmail(mail);
        verify(repository).save(updatedUser);
        verify(convertor).convert(savedUser);

    }

    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "mail@gmail.com";

        UpdateUserRequest request = new UpdateUserRequest("firstName2", "lastName2", "middleName2");
        User user = new User(1L, mail, "firstName", "lastName", true);

        when(repository.findByEmail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(mail, request));

        verify(repository).findByEmail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(convertor);

    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldThrowUserNotActiveException(){

        String mail = "mail@gmail.com";
        UpdateUserRequest request = new UpdateUserRequest("firstName2", "lastName2", "middleName2");
        User user = new User(1L, mail, "firstName", "lastName", false);

        when(repository.findByEmail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, () -> userService.updateUser(mail, request));

        verify(repository).findByEmail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(convertor);

    }

    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActiveFalse(){

        String mail = "mail@gmail.com";
        User user = new User(userId, mail, "firstName", "lastName", true);
        User savedUser = new User(userId, mail, "firstName", "lastName", false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deactivateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deactivateUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActiveTrue(){

        String mail = "mail@gmail.com";
        User user = new User(userId, mail, "firstName", "lastName", false);
        User savedUser = new User(userId, mail, "firstName", "lastName", true);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.activateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldDeleteUser(){

        String mail = "mail@gmail.com";
        User user = new User(userId, mail, "firstName", "lastName", false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(repository).findById(userId);
        verify(repository).deleteById(userId);

    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

}