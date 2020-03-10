//package com.nixsolutions.web;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserControllerTest {
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Before
//    public void setUp() throws Exception {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/");
//        viewResolver.setSuffix(".jsp");
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
//            .setViewResolvers(viewResolver).build();
//    }
//
//    @Test
//    public void testUser() throws Exception {
//        mockMvc.perform(get("/user"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("user"));
//    }
//
//}
