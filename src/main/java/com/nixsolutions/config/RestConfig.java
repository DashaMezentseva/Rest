package com.nixsolutions.config;

import com.nixsolutions.resource.LoginResource;
import com.nixsolutions.resource.UserResource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
            Arrays.asList(
                UserResource.class,
                LoginResource.class));
//                AdminRestController.class,
//                UserRestController.class));
    }
}



