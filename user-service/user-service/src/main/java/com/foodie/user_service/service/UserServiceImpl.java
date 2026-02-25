package com.foodie.user_service.service;

import com.foodie.user_service.entity.UserEntity;
import com.foodie.user_service.repository.UserRepository;
import com.foodie.user_service.util.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return (UserEntity) userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

//    @Override
//    public UserEntity findUserProfileByJwt(String jwt) {
//        String email = JWTUtil.getEmailFromJwtToken(jwt);
//
//
//        UserEntity user = UserRepository.findByEmail(email);
//
//        if (user == null) {
//            throw new UserException("user not exist with email " + email);
//        }
////System.out.println("email user "+user.get().getEmail());
//        return user;
//    }

}
