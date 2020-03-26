//package com.nixsolutions.soap;
//
//import com.nixsolutions.domain.User;
//import com.nixsolutions.repository.UserRepository;
//import java.util.List;
//import javax.jws.WebService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@WebService(endpointInterface = "com.nixsolutions.soap.SoapService")
//@Component
//public class SoapServiceImpl implements SoapService{
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Transactional
//    public void create(User user) {
//        userRepository.saveAndFlush(user);
//    }
//
//    @Transactional
//    public void update(User user) {
//        userRepository.saveAndFlush(user);
//    }
//
//    @Transactional
//    public void remove(User user) {
//        userRepository.delete(user);
//    }
//
//
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public User findByLogin(String login) {
//        return userRepository.findByLogin(login);
//    }
//
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public User findById(Long id) {
//        return userRepository.findByUserId(id);
//    }
//}
