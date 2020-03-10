package com.nixsolutions.service;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import com.nixsolutions.config.DbConfig;
import com.nixsolutions.domain.User;
import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class})
@WebAppConfiguration
@TestExecutionListeners( {DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class UserServiceTest {

    @Autowired
    private RoleDao roleService;
    @Autowired
    private UserDao userService;

    User user;
    private static final String[] IGNORE_COLS = {"userId"};

    @Before
    public void setUp() throws Exception {
        /*        em = emf.createEntityManager();*/
        user = new User(100L, "login1", "password1", "email1"
            , "firstName1", "lastName1", Date.valueOf("1999-08-08"), roleService.findById(1L));
    }

    @Test
    public void testSaveUser() throws Exception {
        try {
            userService.create(null);
            fail("should be threw InvalidDataAccessApiUsageException when pass null");
        } catch (InvalidDataAccessApiUsageException e) {
        } catch (Exception e) {
            fail("should be threw InvalidDataAccessApiUsageException when pass null");
        }
        userService.create(user);
        User actualUser = userService.findByLogin(user.getLogin());
        assertEquals(user.getLogin(), actualUser.getLogin());
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getPassword(), actualUser.getPassword());
        userService.remove(actualUser);
    }

    @Test
    public void testUpdateUser() {
        try {
            userService.update(null);
            fail("should be threw InvalidDataAccessApiUsageException when pass null");
        } catch (InvalidDataAccessApiUsageException e) {
        } catch (Exception e) {
            fail("should be threw InvalidDataAccessApiUsageException when pass null");
        }
        userService.create(user);
        User actualUser = userService.findByLogin(user.getLogin());
        assertEquals(user.getLogin(), actualUser.getLogin());
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getPassword(), actualUser.getPassword());
        actualUser.setLogin("testUser");
        userService.update(actualUser);
        User actualTestUser = userService.findByLogin("testUser");
        assertEquals(actualUser.getLogin(), actualTestUser.getLogin());
        assertEquals(actualUser.getEmail(), actualTestUser.getEmail());
        assertEquals(actualUser.getPassword(), actualTestUser.getPassword());
        userService.remove(actualTestUser);
    }

    @Test
    public void testRemoveUser() {
        try {
            userService.remove((User) null);
            fail("should be threw InvalidDataAccessApiUsageException when pass null");
        } catch (InvalidDataAccessApiUsageException e) {
        } catch (Exception e) {
            fail("should be threw InvalidDataAccessApiUsageException when pass null");
        }
        userService.create(user);
        User createdUser = userService.findByLogin(user.getLogin());
        assertEquals(user.getLogin(), createdUser.getLogin());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        userService.remove(createdUser);
        assertNull(userService.findByLogin(createdUser.getLogin()));
    }

    @Test
    public void testFindAll() {
        userService.create(user);
        List<User> users = userService.findAll();
        assertEquals(user.getLogin(), users.get(users.size() - 1).getLogin());
        assertEquals(user.getEmail(), users.get(users.size() - 1).getEmail());
        assertEquals(user.getPassword(), users.get(users.size() - 1).getPassword());
        userService.remove(userService.findByLogin(user.getLogin()));
    }

    @Test
    public void testFindByLogin() {
        userService.create(user);
        User actualUser = userService.findByLogin(user.getLogin());
        assertEquals(user.getLogin(), actualUser.getLogin());
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getPassword(), actualUser.getPassword());
        userService.remove(actualUser);
    }

    @Test
    public void testFindByEmail() {
        userService.create(user);
        User actualUser = userService.findByEmail(user.getEmail());
        assertEquals(user.getLogin(), actualUser.getLogin());
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getPassword(), actualUser.getPassword());
        userService.remove(actualUser);
    }

}
