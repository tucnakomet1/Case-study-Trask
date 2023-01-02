package com.example.case_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaseStudy {
    /*
     * url:       http://localhost:8989/h2-console
     * JDBC URL:  jdbc:h2:mem:case_study
     * User Name: sa
     * Password:
     */

    public static void main(String[] args) {
        SpringApplication.run(CaseStudy.class, args);
    }
}
