package com.nixsolutions.rest;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.dto.UserDto;
import com.nixsolutions.form.LoginForm;
import com.nixsolutions.service.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.nixsolutions.dto.UserDto.dtoToUser;
import static com.nixsolutions.dto.UserDto.userToDto;

@CrossOrigin(origins = "http://192.168.0.107:4200", allowedHeaders = "*")
@RestController
@Path("/")
public class LoginResource {
    final Logger log = LoggerFactory.getLogger(LoginResource.class);
    private final String messageWrong = new String("{\"message\":\"invalid Credentials\"}");

    @Autowired
    private UserDao userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response postUser(@RequestBody LoginForm credentials) {
        String login = credentials.getLogin();
        User user = userService.findByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(credentials.getPassword())) {
                return Response
                        .status(Response.Status.OK)
                        .entity(user)
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
        }
        return Response
                .status(Response.Status.OK)
                .entity(messageWrong)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/login/{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public User findUserByLogin(@PathParam("login") String login) {
        return userService.findByLogin(login);
    }

    @GET
    @Path("/loginDto/{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public UserDto findUserDtoByLogin(@PathParam("login") String login) {
        System.out.println(userService.findByLogin(login));
        User user = userService.findByLogin(login);
        UserDto userDto = userToDto(user);
        return userDto;

    }
    @POST
    @Path("/registration")
    @Produces({MediaType.APPLICATION_JSON})
    public void postUser(UserDto user) {
        log.trace("in registration");
        user.setRole(new Role(1L, "ROLE_USER"));
        userService.create(dtoToUser(user));
    }
}


