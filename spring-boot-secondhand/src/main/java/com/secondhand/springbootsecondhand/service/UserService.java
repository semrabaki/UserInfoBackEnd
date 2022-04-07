package com.secondhand.springbootsecondhand.service;

import com.secondhand.springbootsecondhand.dto.CreateUserRequest;
import com.secondhand.springbootsecondhand.dto.UpdateUserRequest;
import com.secondhand.springbootsecondhand.dto.UserDto;
import com.secondhand.springbootsecondhand.dto.UserDtoConvertor;
import com.secondhand.springbootsecondhand.exception.UserIsNotActiveException;
import com.secondhand.springbootsecondhand.exception.UserNotFoundException;
import com.secondhand.springbootsecondhand.model.User;
import com.secondhand.springbootsecondhand.repository.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private  final UserDtoConvertor userDtoConvertor;
    public UserService(UserRepository userRepository,
                       UserDtoConvertor userDtoConvertor) {
        this.userRepository = userRepository;
        this.userDtoConvertor = userDtoConvertor;
    }
    public List<UserDto> getAllUser() {
        //after testing
        //return userDtoConvertor.convert(userRepository.findAll());
        //before testing
        return userRepository.findAll()
                                    .stream()
                                    .map(userDtoConvertor::convert)
                                    .collect(Collectors.toList());
    }
    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        System.out.println(user);
        return userDtoConvertor.convert(user);
    }

    public UserDto createUser(final CreateUserRequest userRequest) {
        User user = new User(userRequest.getEmail(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                false);

        return userDtoConvertor.convert(userRepository.save(user));
    }

    public UserDto updateUser(final String mail, final UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        if (!user.getActive()){
            throw new UserIsNotActiveException();
        }
        User updatedUser = new User(user.getId(),
                                    user.getEmail(),
                                    updateUserRequest.getFirstName(),
                                    updateUserRequest.getLastName(),
                                    user.getActive());

        return userDtoConvertor.convert(userRepository.save(updatedUser));
    }
    public void deactivateUser(final Long id) {
        changeActivateUser(id, false);
    }

    public void activateUser(final Long id) {
        changeActivateUser(id, true);
    }

    public void deleteUser(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

//    private boolean doesUserExist(Long id){
//        return userRepository.existsById(id);
//    }
    private void changeActivateUser(final Long id, Boolean isActive){
        User user = findUserById(id);
        User updatedUser = new User(user.getId(),
                                        user.getEmail(),
                                        user.getFirstName(),
                                        user.getLastName(),
                                        isActive);
        userRepository.save(updatedUser);
    }
    private User findUserByMail(String mail){
        return userRepository.findByEmail(mail)
                .orElseThrow(() -> new UserNotFoundException("User could not be found"));
    }
    protected User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User could not be found: " + id));
    }
}
