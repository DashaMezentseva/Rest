package com.nixsolutions.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import java.sql.Date;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserResourceTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }
//
//    Role role1 = new Role(1L, "aaa");

    @Test
    public void testFindAll() {
        Response response = target("/rest/users").request().get();
        assertEquals("should return status 200", 200, response.getStatus());
        assertNotNull("Should return user list", response.getEntity().toString());
//        System.out.println(response.getStatus());
//        System.out.println(response.readEntity(String.class));
    }
//
//    @Test
//    public void testGetById() {
//        Response output = target("/users/20").request().get();
//        assertEquals("Should return status 200", 200, output.getStatus());
//        assertNotNull("Should return user object as json", output.getEntity());
//        System.out.println(output.getStatus());
//        System.out.println(output.readEntity(String.class));
//    }
//
//    @Test
//    public void testCreate() {
//        User user = new User(105L, "aaa", "aaa", "aaa@gmail.com", "aaa", "aaa", Date.valueOf("1996-02-06"), role1);
//        Response output = target("/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
//        System.out.println(output.getStatus());
//        assertEquals("Should return status 201", 201, output.getStatus());
//    }
//
//    @Test
//    public void testUpdate() {
//        User user = new User(105L, "aaa", "aaa", "aaa@gmail.com", "aaa", "aaa", Date.valueOf("1996-02-06"), role1);
//        Response output = target("/users/user/101").request().put(Entity.entity(user, MediaType.APPLICATION_JSON));
//        assertEquals("Should return status 204", 204, output.getStatus());
//        System.out.println(output.getStatus());
//    }
//
//    @Test
//    public void testDelete() {
//        Response output = target("/users/user/105").request().delete();
//        assertEquals("Should return status 204", 204, output.getStatus());
//    }
}


