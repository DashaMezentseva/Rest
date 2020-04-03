package com.nixsolutions.rest;

import com.nixsolutions.domain.User;
import com.nixsolutions.dto.UserDto;
import com.nixsolutions.service.UserDao;

import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nixsolutions.dto.UserDto.dtoToUser;
import static com.nixsolutions.dto.UserDto.userToDto;

@CrossOrigin
@RestController
@Path("/")
public class UserResource {
    final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserDao userService;

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUsers() {
        log.trace("in findAllUsers");
        return userService.findAll();
    }

    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(@PathParam("userId") Long userId) {
        log.trace("in findAllUsers, userId = " + userId);
        return userService.findById(userId);
    }

    @GET
    @Path("/usersDto/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto findUserDtoById(@PathParam("userId") Long userId) {
        log.trace("in findAllUsers, userId = " + userId);
        User user = userService.findById(userId);
        UserDto userDto = userToDto(user);
        return userDto;
    }

    @POST
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON})
    public Response postUser(UserDto user) {
        log.trace("in postUser");
        if (!isUniqueLogin(user)) {
            return Response.status(Response.Status.CONFLICT).entity("Login is not unique ").build();
        } else if (!isUniqueEmail(user)) {
            return Response.status(Response.Status.CONFLICT).entity("Email is not unique ").build();
        } else {
            userService.create(dtoToUser(user));
        }
        return Response.ok().build();
    }


    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response putUser(@PathParam("userId") Long userId, @PathParam("email") String email, @RequestBody UserDto user) {
        log.trace("in putUser, userId = " + userId + " email = " + email);
        if (userService.findById(userId) != null) {

            if (!isUniqueEmail(user)) {
                return Response.status(Response.Status.CONFLICT).entity("Email is not unique " + email).build();
            } else {
                user.setUserId(userId);
                userService.create(dtoToUser(user));
            }
        }
        return Response.ok().build();
    }

    @Path("/users/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    @DELETE
    public void deleteUser(@PathParam("userId") Long userId) {
        log.trace("in deleteUser, userId = " + userId);
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException();
        }
        userService.remove(user);
    }

    protected boolean isUniqueEmail(UserDto userDto) {
        for (User user : userService.findAll()) {
            if (user.getEmail().equals(userDto.getEmail())) {
                return false;
            }
        }
        return true;
    }

    protected boolean isUniqueLogin(UserDto userDto) {
        for (User user : userService.findAll()) {
            if (user.getLogin().equals(userDto.getLogin())) {
                return false;
            }
        }
        return true;
    }
}
