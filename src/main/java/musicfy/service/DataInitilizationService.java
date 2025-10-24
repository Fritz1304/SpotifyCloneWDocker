package musicfy.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import musicfy.model.User;
import musicfy.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataInitilizationService implements CommandLineRunner {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    public void run(String... args) throws Exception {
        createDefaultAdminUser();
    }
   
    private void createDefaultAdminUser() {
        /// Check if admin user exists
        if(!userRepository.existsByEmail("admin@musicfy.com")){
        	 User adminUser = User.builder()
                     .email("admin@musicfy.com")
                     .password(passwordEncoder.encode("admin123"))
                     .rol(User.Role.ADMIN)
                     .build();
            
            userRepository.save(adminUser);
            log.info("Admin user created");
        }else{
            log.info("Admin user already exists");
        }
    }

}
