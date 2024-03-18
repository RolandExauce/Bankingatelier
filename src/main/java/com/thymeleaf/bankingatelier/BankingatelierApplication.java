// app entry file
package com.thymeleaf.bankingatelier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//NOTE: spring typically uses the MVC architecture,
// the controller interacts with the service layer, and the service layer
//interacts with repository which interacts with the database (or other data sources)

@SpringBootApplication
public class BankingatelierApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankingatelierApplication.class, args);
	}
}
