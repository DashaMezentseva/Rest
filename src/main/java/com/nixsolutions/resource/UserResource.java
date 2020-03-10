package com.nixsolutions.resource;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Path("/users")
public class UserResource {
    @Autowired
    private UserDao userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("userId") Long userId) {
        return userService.findById(userId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid UserDto userDto, @PathParam("passwordAgain") String passwordAgain) {
        if (!isUniqueLogin(userDto)) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        if (userDto.getPassword().isEmpty()) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        if (passwordAgain.isEmpty()) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        if (!passwordAgain.equals(userDto.getPassword())) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        User user = UserDto.dtoToUser(userDto);
        userService.create(user);
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") Long userId, @Valid UserDto userDto,
                               @PathParam("password") String password,
                               @PathParam("passwordAgain") String passwordAgain) {

        User user = userService.findByLogin(userDto.getLogin());
        user.setUserId(userId);
        if (password.isEmpty() && passwordAgain.isEmpty()) {
            userDto.setPassword(user.getPassword());
            passwordAgain = user.getPassword();
        } else if (!passwordAgain.equals(password)) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        User user1 = UserDto.dtoToUser(userDto);
        userService.update(user1);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userService.remove(user);
        return Response.status(202).entity("User deleted successfully !!").build();
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