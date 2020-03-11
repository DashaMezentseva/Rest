package com.nixsolutions.config;

import com.nixsolutions.soap.SoapService;
import javax.xml.ws.Endpoint;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.nixsolutions.service")
@Import({DbConfig.class})
public class SoapConfig {

    @Autowired
    private SoapService soapService;

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(cxf(), soapService);
        endpoint.publish("/soapService");
        return endpoint;
    }

}
