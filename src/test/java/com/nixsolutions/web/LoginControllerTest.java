//package com.nixsolutions.web;
//
//import static org.mockito.Matchers.anyLong;
//import static org.mockito.Mockito.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
//import com.google.code.kaptcha.Constants;
//import com.nixsolutions.domain.Role;
//import com.nixsolutions.domain.User;
//import com.nixsolutions.service.RoleDao;
//import com.nixsolutions.service.UserDao;
//import com.nixsolutions.web.LoginController;
//import java.security.Principal;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@RunWith(MockitoJUnitRunner.class)
//public class LoginControllerTest {
//
//    @Autowired
//    private UserDao userService;
//    @Autowired
//    private RoleDao roleService;
//    @Mock
//    private User user;
//    @Mock
//    private Role role;
//    private static final String CAPTCHA = "Db5g";
//
//    @InjectMocks
//    private LoginController loginController;
//
//    private MockMvc mockMvc;
//
//    public LoginControllerTest() {
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/");
//        viewResolver.setSuffix(".jsp");
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController)
//            .setViewResolvers(viewResolver).build();
//    }
//
//    @Test
//    public void testLogout() throws Exception {
//        mockMvc.perform(get("/logout"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(redirectedUrl("login"));
//    }
//
//    @Test
//    public void testLogin() throws Exception {
//        mockMvc.perform(get("/login"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("login"));
//    }
//
//
//    @Test
//    public void testRegistration() throws Exception {
//        mockMvc.perform(get("/registration"))
//            .andExpect(status().isOk())
//            .andExpect(model().attributeExists("registrationUserDto"))
//            .andExpect(model().attributeDoesNotExist("roles"))
//            .andExpect(view().name("registration"));
//    }
//
//}
