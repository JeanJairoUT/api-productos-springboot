package pe.edu.utp.api_productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"pe.edu.utp.api_productos", "controller", "service", "model", "repository"})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "model")
public class ApiProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProductosApplication.class, args);
	}

}
