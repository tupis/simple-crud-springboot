package com.tupi.services.impl;

import com.tupi.controllers.impl.PersonControllerImpl;
import com.tupi.data.vo.v1.PersonVO;
import com.tupi.data.vo.v1.UserVO;
import com.tupi.exceptions.PersonNotNullException;
import com.tupi.exceptions.ResourceNotFoundException;
import com.tupi.mapper.DozerMapper;
import com.tupi.models.Person;
import com.tupi.repositories.PersonRepository;
import com.tupi.repositories.UserRepository;
import com.tupi.services.PersonService;
import com.tupi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServicesImpl implements UserService {
    private final Logger logger = Logger.getLogger(UserServicesImpl.class.getName());

    @Autowired
    private UserRepository repository;

    public UserServicesImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<UserVO> findAll() {
        return null;
    }

    @Override
    public UserVO findById(Long id) {
        return null;
    }

    @Override
    public UserVO create(UserVO person) {
        return null;
    }

    @Override
    public UserVO update(Long id, UserVO person) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Find one user by name " + username + "!");

        var result = this.repository.findUsersByUserName(username);

        if(result != null) {
            return result;
        } else {
            throw new UsernameNotFoundException("Username " + username +  "not found!");
        }


    }
}
