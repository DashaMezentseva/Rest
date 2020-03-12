//package com.nixsolutions.rest;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import com.nixsolutions.domain.Role;
//import com.nixsolutions.domain.User;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import org.glassfish.jersey.test.TestProperties;
//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class UserResourceTest extends JerseyTest {
//
//
//
//    @Override
//    protected Application configure() {
//        enable(TestProperties.LOG_TRAFFIC);
//        enable(TestProperties.DUMP_ENTITY);
//        final ResourceConfig resourceConfig = new ResourceConfig(UserResource.class);
//
//        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.refresh();
//        resourceConfig.property("contextConfig", context);
//        return resourceConfig;
//    }
//
//    @Test
//    public void testFindAllUsers() {
//        Response output = target("/users").request().get();
//        assertEquals("should return status 200", 200, output.getStatus());
//        assertNotNull("Should return list of users", output.getEntity());
//    }
//
//    @Test
//    public void testFindUserById() {
//        Response output = target("/users/16").request().get();
//        assertEquals("Should return status 200", 200, output.getStatus());
//        assertNotNull("Should return notification", output.getEntity());
//    }
//
//    @Test
//    public void testFindUserByIdNotExist() {
//        Response output = target("/users/no-id-digit").request().get();
//        assertEquals("Should return status 404", 404, output.getStatus());
//    }
//
//    @Test
//    public void testAddUser() {
//        User testUser = new User();
//        Role testRole = new Role();
//        testRole.setRoleId(2L);
//        testRole.setName("ROLE_ADMIN");
//
//        testUser = new User();
//        testUser.setUserId(1L);
//        testUser.setLogin("login");
//        testUser.setPassword("password");
//        testUser.setEmail("email@gmail.com");
//        testUser.setFirstName("firstName");
//        testUser.setLastName("lastName");
//        testUser.setBirthday(parseDate("1999-08-08"));
//        testUser.setRole(testRole);
//
//        Response output = target("/users")
//            .request()
//            .post(Entity.entity(testUser, MediaType.APPLICATION_JSON));
//
//        assertEquals("Should return status 200", 200, output.getStatus());
//        assertNotNull("Should return user", output.getEntity());
//    }
//
//    public static Date parseDate(String date) {
//        try {
//            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        } catch (ParseException e) {
//            return null;
//        }
//    }
//
//    @Test
//    public void testUpdate() {
//        User testUser = new User();
//        Role testRole = new Role();
//        testRole.setRoleId(2L);
//        testRole.setName("ROLE_ADMIN");
//
//        testUser = new User();
//        testUser.setUserId(1L);
//        testUser.setLogin("login");
//        testUser.setPassword("password");
//        testUser.setEmail("email@gmail.com");
//        testUser.setFirstName("firstName");
//        testUser.setLastName("lastName");
//        testUser.setBirthday(parseDate("1999-08-08"));
//        testUser.setRole(testRole);
//        Response output = target("/users")
//            .request()
//            .put(Entity.entity(testUser, MediaType.APPLICATION_JSON));
//        assertEquals("Should return status 204", 204, output.getStatus());
//    }
//
//    @Test
//    public void testDelete() {
//        Response output = target("/users/1").request().delete();
//        assertEquals("Should return status 204", 204, output.getStatus());
//    }
//}
//
//
