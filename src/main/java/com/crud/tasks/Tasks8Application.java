package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Tasks8Application extends SpringBootServletInitializer {

    public static void main(String[] args) {SpringApplication.run(Tasks8Application.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
        return application.sources(Tasks8Application.class);
    }

}
