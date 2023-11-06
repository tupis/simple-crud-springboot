package com.tupi.services;

import com.tupi.data.vo.v1.UserVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserVO> findAll();
    UserVO findById(Long id);
    UserVO create(UserVO person);
    UserVO update(Long id, UserVO person);
    ResponseEntity<String> deleteById(Long id);
}
