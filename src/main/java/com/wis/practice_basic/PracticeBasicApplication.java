package com.wis.practice_basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//add corresponding folder
@SpringBootApplication(scanBasePackages = {
        "com.wis.*"
})
public class PracticeBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeBasicApplication.class, args);
    }

}
