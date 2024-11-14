package pe.edu.uni.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ReparacionesApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ReparacionesApiApplication.class,args);
	}
}
