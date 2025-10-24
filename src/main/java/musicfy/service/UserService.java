package musicfy.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import musicfy.dto.RegisterRequest;
import musicfy.dto.UserResponse;
import musicfy.model.User;
import musicfy.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserResponse registerUser(RegisterRequest request) {
		
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("User already exists");
		}
		
		User newUser = new User();
		
		newUser.setEmail(request.getEmail());
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		newUser.setRol(User.Role.USER);
		
		userRepository.save(newUser);	
		
		return new UserResponse(
				newUser.getId(),
				newUser.getEmail(),
				UserResponse.Role.USER);
	}


	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
	}

}
