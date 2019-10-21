package com.jmrsoftware.csvtobeanapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan(basePackages = "com.jmrsoftware.csvtobeanapplication")
public class CsvToBeanApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CsvToBeanApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CsvToBeanApplication.class);
    }
}
