package com.example.renzhubserver.service;

import com.example.renzhubserver.user.User;
import com.example.renzhubserver.repository.UserRepository;
import com.example.renzhubserver.user.dto.response.UserRegisterResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional //Transaction 안에서 모든게 일어나야한다.
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    public UserRegisterResponseDto join(User user){
//        validateDuplicateMemeber(user);
        userRepository.save(user);
        return new UserRegisterResponseDto(true);
    }

    //  아이디 중복검사
    private void validateDuplicateMemeber(User user){
        List<User> findsMembers = userRepository.findByUserId(user.getUserId());
        if(!findsMembers.isEmpty())
            throw  new IllegalStateException("이미 존재하는 아이디.");
    }
}
