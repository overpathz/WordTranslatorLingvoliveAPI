package org.overpathz.lingvolive.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.overpathz.lingvolive.rest.configuration.MyConfig;
import org.overpathz.lingvolive.rest.service.TranslateAPI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) throws JsonProcessingException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        TranslateAPI translateAPI = context.getBean("translate", TranslateAPI.class);

        while (true) {
            translateAPI.translate();
        }

    }
}
