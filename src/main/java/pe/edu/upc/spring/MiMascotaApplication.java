package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MiMascotaApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(MiMascotaApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		String contra = "pet";
		
		for(int i = 0; i < 2; i++) {
			String bCryptPassword1 = passwordEncoder.encode(password);
			System.out.println(bCryptPassword1);
		}
		
		for(int i = 0; i < 2; i++) {
			String bCryptPassword2 = passwordEncoder.encode(contra);
			System.out.println(bCryptPassword2);
		}
	}

}
