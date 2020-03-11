package com.nixsolutions.soap;

import com.nixsolutions.domain.User;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.stereotype.Component;

@WebService
@Component
public interface SoapService {

    @WebMethod
    void create(User user);

    @WebMethod
    void update(User user);

    @WebMethod
    void remove(User user);

    @WebMethod
    List<User> findAll();

    @WebMethod
    User findByLogin(String login);

    @WebMethod
    User findByEmail(String email);

    @WebMethod
    User findById(Long id);
}
