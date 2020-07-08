package zq.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("zq.demo.*.dao")
public class LoginApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}
	
}
