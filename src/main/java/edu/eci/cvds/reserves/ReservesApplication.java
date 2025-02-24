package edu.eci.cvds.reserves;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to start the ISIBook application with Spring Boot.
 */
@SpringBootApplication
public class ReservesApplication {

	/**
	 * Main method to start the app.
	 * 
	 * @param args Arguments to start the app.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ReservesApplication.class, args);
	}

}
