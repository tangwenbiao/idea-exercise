package com.idea.exercise.authselection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/16 17:28
 */
@SpringBootApplication
@ComponentScan({"com.idea.exercise.authselection"})
public class AuthTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthTestApplication.class);
  }

}
