package ca.gb.sf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"ca.gb.sf"})
@EnableJpaRepositories(basePackages="ca.gb.sf.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="ca.gb.sf.models")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}