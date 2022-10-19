package uz.bakhromjon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootRedisAsCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisAsCacheApplication.class, args);
	}

}
