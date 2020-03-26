package com.nixsolutions.service;

import com.nixsolutions.domain.User;

import com.nixsolutions.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@EnableTransactionManagement
public class UserService implements UserDao {

    final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        try {
            log.trace("Call method create");
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            log.error("Error in time create");
            throw e;
        }
    }

    public void update(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error in time update");
            throw e;
        }

    }

    public void remove(User user) {
        try {
            log.trace("Call method delete");
            userRepository.delete(user);
        } catch (Exception e) {
            log.error("Error in time delete");
            throw e;
        }

    }

    public List<User> findAll() {
        try {
            log.trace("Call method findAll");
            return userRepository.findAll();
        } catch (Exception e) {
            log.error("Error in time findAll", e);
            throw e;
        }
    }

    public User findByLogin(String login) {
        try {
            log.trace("Call method findBy Login");
            return userRepository.findByLogin(login);
            //return userToDto(userRepository.findByLogin(login));
        } catch (Exception e) {
            log.error("Error in time findByLogin");
            throw e;
        }
    }

    public User findByEmail(String email) {
        try {
            log.trace("Call method findAll");
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            log.error("Error in time findByEmail");
            throw e;
        }
    }

    public User findById(Long id) {
        try {
            log.trace("Call method findById");
            return userRepository.findByUserId(id);
        } catch (Exception e) {
            log.error("Error in time findById");
            throw e;
        }
    }

    public Optional<User> findOneByLogin(String login) {
        if (login == null) {
            log.error("Email == null", new NullPointerException());
            throw new NullPointerException();
        }
        try {
            log.trace("Call method findOneByLogin");
            return userRepository.findOneByLogin(login);
        } catch (Exception e) {
            log.error("Error in time findOneByLogin", e);
            throw e;
        }
    }
}


