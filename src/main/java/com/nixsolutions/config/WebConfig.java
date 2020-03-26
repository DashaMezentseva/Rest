package com.nixsolutions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
//import com.nixsolutions.domain.Role;
//import com.nixsolutions.dto.RoleFormatter;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.format.Formatter;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@EnableWebMvc
//@Configuration
//@ComponentScan("com.nixsolutions")
//@Import({SecurityConfig.class})
//public class WebConfig implements WebMvcConfigurer {
//
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setExposeContextBeansAsAttributes(true);
//        return viewResolver;
//    }
//
//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
//
//    @Bean
//    public Formatter<Role> roleFormatter(){
//        return new RoleFormatter();
//    }
//
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatterForFieldType(Role.class, roleFormatter());
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new UserDetailsServiceImpl();
////    };
//
//}
//
