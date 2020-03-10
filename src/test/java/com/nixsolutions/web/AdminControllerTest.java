//package com.nixsolutions.web;
//
//import com.google.code.kaptcha.Constants;
//import com.nixsolutions.domain.Role;
//import com.nixsolutions.domain.User;
//import com.nixsolutions.service.RoleDao;
//import com.nixsolutions.service.UserDao;
//import java.util.List;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatcher;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AdminControllerTest {
//
//    @Mock
//    private UserDao userService;
//    @Mock
//    private RoleDao roleService;
//    @Mock
//    private User user;
//    @Mock
//    private Role role;
//    @InjectMocks
//    private AdminController adminController;
//
//    private MockMvc mockMvc;
//
//    public AdminControllerTest() {
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/");
//        viewResolver.setSuffix(".jsp");
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController)
//            .setViewResolvers(viewResolver).build();
//    }
//
//    @Test
//    public void testUserList() throws Exception {
//        List<User> userList = mock(ArrayList.class);
//        when(userService.findAll()).thenReturn(userList);
//        mockMvc.perform(get("/admin"))
//            .andExpect(status().isOk())
//            .andExpect(model().attribute("users", userList))
//            .andExpect(view().name("admin"));
//
//        verify(userService, atLeastOnce()).findAll();
//    }
//
//    @Test
//    public void testEditUser() throws Exception {
//        Long id = 2L;
//        when(userService.findById(id)).thenReturn(user);
//        when(roleService.findAll()).thenReturn(new ArrayList<Role>());
//
//        mockMvc.perform(get("/edit").param("userId", String.valueOf(id)))
//            .andExpect(status().isOk())
//            .andExpect(model().attributeExists("roles"))
//            .andExpect(model().attributeExists("userDto"))
//            .andExpect(view().name("edit"));
//
//        verify(userService, times(1)).findById(id);
//        verify(roleService, times(1)).findAll();
//    }
//
//
//    @Test
//    public void testAddUser() throws Exception {
//        when(roleService.findAll()).thenReturn(new ArrayList<Role>());
//        mockMvc.perform(get("/add"))
//            .andExpect(status().isOk())
//            .andExpect(model().attributeExists("roles"))
//            .andExpect(model().attributeExists("userDto"))
//            .andExpect(view().name("add"));
//
//        verify(roleService, times(1)).findAll();
//    }
//
//    @Test
//    public void testSubmitAddUserNoErrors() throws Exception {
//        doNothing().when(userService).create(user);
//        mockMvc.perform(post("/add")
//            .param("login", "login")
//            .param("password", "password")
//            .param("passwordAgain", "password")
//            .param("email", "email@gmail.com")
//            .param("firstName", "firstName")
//            .param("lastName", "lastName")
//            .param("birthday", "1990-08-08"))
//            .andExpect(model().size(3))
//            .andExpect(model().hasNoErrors())
//            .andExpect(model().attributeExists("userDto"))
//            .andExpect(model().attributeDoesNotExist("roles"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(redirectedUrl("admin?error=user+successfully+created"));
//
//        verify(userService, atLeastOnce()).create(any());
//    }
//
//    @Test
//    public void testSubmitAddUserErrors() throws Exception {
//        doNothing().when(userService).create(user);
//        when(roleService.findAll()).thenReturn(new ArrayList<Role>());
//        when(userService.findAll()).thenReturn(new ArrayList<User>());
//        mockMvc.perform(post("/add")
//            .param("login", "login")
//            .param("password", "password")
//            .param("passwordAgain", "password1234")
//            .param("email", "email@gmail.com")
//            .param("firstName", "firstName")
//            .param("lastName", "lastName")
//            .param("birthday", "1990-08-08"))
//            .andExpect(model().attributeExists("roles"))
//            .andExpect(model().attributeExists("userDto"))
//            .andExpect(model().attributeErrorCount("userDto", 1))
//            .andExpect(model().attributeHasFieldErrors("userDto", "password"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("add"));
//
//        verify(roleService, atLeastOnce()).findAll();
//        verify(userService, atLeastOnce()).findAll();
//
//    }
//
//}