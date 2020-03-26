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

@CrossOrigin(origins = "http://192.168.0.107:4200", allowedHeaders = "*")
@RestController
@Path("/")
public class UserResource {
    final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserDao userService;

    @GET
    @Path("/users")
    @CrossOrigin(origins = "http://192.168.0.107:4200")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUsers() {
        log.trace("in findAllUsers");
        return userService.findAll();
    }

    @GET
    @Path("/users/{userId}")
    @CrossOrigin(origins = "http://192.168.0.107:4200")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserById(@PathParam("userId") Long userId) {
        log.trace("in findAllUsers, userId = " + userId);
        return userService.findById(userId);
    }

    @GET
    @Path("/usersDto/{userId}")
    @CrossOrigin(origins = "http://192.168.0.107:4200")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto findUserDtoById(@PathParam("userId") Long userId) {
        log.trace("in findAllUsers, userId = " + userId);
        User user = userService.findById(userId);
        UserDto userDto = userToDto(user);
        return userDto;
    }

    @POST
    @Path("/users")
    @CrossOrigin(origins = "http://192.168.0.107:4200")
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
    @CrossOrigin(origins = "http://192.168.0.107:4200")
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
    @CrossOrigin(origins = "http://192.168.0.107:4200")
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

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addUser(@Valid UserDto userDto, @PathParam("passwordAgain") String passwordAgain) {
//        if (!isUniqueLogin(userDto)) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//
//        if (userDto.getPassword().isEmpty()) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//
//        if (passwordAgain.isEmpty()) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//
//        if (!passwordAgain.equals(userDto.getPassword())) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//
//        User user = UserDto.dtoToUser(userDto);
//        userService.create(user);
//        return Response.status(Response.Status.CREATED).build();
//    }
//
//
//    @PUT
//    @Path("/{userId}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateUser(@PathParam("userId") Long userId, @Valid UserDto userDto,
//                               @PathParam("password") String password,
//                               @PathParam("passwordAgain") String passwordAgain) {
//
//        User user = userService.findByLogin(userDto.getLogin());
//        user.setUserId(userId);
//        if (password.isEmpty() && passwordAgain.isEmpty()) {
//            userDto.setPassword(user.getPassword());
//            passwordAgain = user.getPassword();
//        } else if (!passwordAgain.equals(password)) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//        User user1 = UserDto.dtoToUser(userDto);
//        userService.update(user1);
//
//        return Response.noContent().build();
//    }


//    protected boolean isUniqueLogin(UserDto userDto) {
//        for (User user : userService.findAll()) {
//            if (user.getLogin().equals(userDto.getLogin())) {
//                return false;
//            }
//        }
//        return true;
//    }

}
