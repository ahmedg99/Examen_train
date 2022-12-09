package tn.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.spring.springboot.Services.Interfaces.ITrainService;

@SpringBootApplication
@EnableJpaRepositories("tn.spring.springboot.repositories")
@ComponentScan(basePackages = {"tn.spring.springboot.Controllers","tn.spring.springboot.Services","tn.spring.springboot.Configurations"})
@EnableSpringConfigured
@EnableAspectJAutoProxy
@EnableScheduling
/*
@ComponentScan({"com.delivery.request"})
@EntityScan("com.delivery.domain")
@EnableJpaRepositories("tn.spring.springboot.Repository")
*/

 public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);



    }

}


